package br.com.edu.zup.ecommerce.images;

import br.com.edu.zup.ecommerce.product.Product;
import br.com.edu.zup.ecommerce.product.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;

@RestController
@RequestMapping("api/images")
public class ImagesController {

    private final ProductRepository productRepository;

    public ImagesController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    @PostMapping("/{id}")
    public ResponseEntity<?> addImages(@PathVariable("id") Long productId, ImagesRequest imagesRequest){

        Optional<Product> productOptional = productRepository.findById(productId);

        if (productOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }



        return ResponseEntity.ok().build();
    }

}
