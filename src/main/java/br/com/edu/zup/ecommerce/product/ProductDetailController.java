package br.com.edu.zup.ecommerce.product;

import br.com.edu.zup.ecommerce.product.review.ReviewRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/products/detail")
public class ProductDetailController {
    //1
    private final ProductRepository productRepository;
    //1
    private final ReviewRepository reviewRepository;

    public ProductDetailController(ProductRepository productRepository, ReviewRepository reviewRepository) {
        this.productRepository = productRepository;
        this.reviewRepository = reviewRepository;
    }

    //1
    @GetMapping("/{id}")
    public ResponseEntity<?> productDetail(@PathVariable(value = "id",required = true) Long productId){

        Optional<Product> productOptional = productRepository.findById(productId);
        //1
        if (productOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }


        Product product = productOptional.get();


        return ResponseEntity.ok().body(new ProductDetailResponse(product,reviewRepository.getAverageReviewsNote()));
    }
}
