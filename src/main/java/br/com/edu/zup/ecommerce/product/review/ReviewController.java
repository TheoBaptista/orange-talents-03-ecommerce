package br.com.edu.zup.ecommerce.product.review;

import br.com.edu.zup.ecommerce.product.Product;
import br.com.edu.zup.ecommerce.product.ProductRepository;
import br.com.edu.zup.ecommerce.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/reviews")
public class ReviewController {
    //2
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    public ReviewController(ProductRepository productRepository, ReviewRepository reviewRepository) {
        this.productRepository = productRepository;
        this.reviewRepository = reviewRepository;
    }

    @Transactional
    @PostMapping("/{id}")   //1
    public ResponseEntity<?> createReview(@PathVariable("id") Long productId, @RequestBody @Valid ReviewRequest reviewRequest, @AuthenticationPrincipal User user){

        Optional<Product> optionalProduct = productRepository.findById(productId);
        //1
        if (optionalProduct.isEmpty()){
           return ResponseEntity.notFound().build();
        }

        Product product = optionalProduct.get();

        reviewRepository.save(reviewRequest.toReview(product,user));

        return ResponseEntity.ok().build();
    }

}
