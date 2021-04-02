package br.com.edu.zup.ecommerce.images;

import br.com.edu.zup.ecommerce.product.Product;
import br.com.edu.zup.ecommerce.product.ProductRepository;
import br.com.edu.zup.ecommerce.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/images")
public class ImagesController {

    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;

    public ImagesController(ProductRepository productRepository, ImageRepository imageRepository) {
        this.productRepository = productRepository;
        this.imageRepository = imageRepository;
    }

    @Transactional
    @PostMapping("/{id}")
    public ResponseEntity<?> addImages(@PathVariable("id") Long productId, @Valid ImagesRequest imagesRequest){

        Optional<Product> productOptional = productRepository.findById(productId);

        if (productOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        if (isNotSameUser(productOptional.get())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        List<Image> imageList = imagesRequest.toImagesList();
        imageRepository.saveAll(imageList);

        Product product = productOptional.get();
        product.setImageList(imageList);
        productRepository.save(product);


        return ResponseEntity.ok().build();
    }

    private boolean isNotSameUser(Product product) {
        User user  = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return !user.getId().equals(product.getUser().getId());
    }

}
