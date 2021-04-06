package br.com.edu.zup.ecommerce.review.controller;

import br.com.edu.zup.ecommerce.product.model.Product;
import br.com.edu.zup.ecommerce.product.repository.ProductRepository;
import br.com.edu.zup.ecommerce.review.dto.ReviewRequest;
import br.com.edu.zup.ecommerce.review.repository.ReviewRepository;
import br.com.edu.zup.ecommerce.user.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/reviews")
public class ReviewController {

    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    public ReviewController(ProductRepository productRepository, ReviewRepository reviewRepository) {
        this.productRepository = productRepository;
        this.reviewRepository = reviewRepository;
    }

    @Transactional
    @PostMapping("/{id}")
    public ResponseEntity<?> createReview(@PathVariable("id") Long productId, @RequestBody @Valid ReviewRequest reviewRequest, @AuthenticationPrincipal User user){

        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isEmpty()){
           return ResponseEntity.notFound().build();
        }

        Product product = optionalProduct.get();

        reviewRepository.save(reviewRequest.toReview(product,user));

        return ResponseEntity.ok().build();
    }

}
