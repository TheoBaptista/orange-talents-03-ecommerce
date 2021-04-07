package br.com.edu.zup.ecommerce.images;

import br.com.edu.zup.ecommerce.product.Product;
import br.com.edu.zup.ecommerce.product.ProductRepository;
import br.com.edu.zup.ecommerce.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/images")
public class ImagesController {

    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;
    private final StorageImages storageImages;

    public ImagesController(ProductRepository productRepository, ImageRepository imageRepository, StorageImages storageImages) {
        this.productRepository = productRepository;
        this.imageRepository = imageRepository;
        this.storageImages = storageImages;
    }

    @Transactional
    @PostMapping("/{id}")
    public ResponseEntity<?> addImages(@PathVariable("id") Long productId, @Valid ImagesRequest imagesRequest, @AuthenticationPrincipal User user){

        Optional<Product> productOptional = productRepository.findById(productId);

        if (productOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Product product = productOptional.get();

        if (product.getUser().equals(user)){


            List<Image> imageList = imagesRequest.toImagesList(storageImages.uploadImages(imagesRequest.getImagesList()));
            imageRepository.saveAll(imageList);


            product.addImageList(imageList);
            productRepository.save(product);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

}
