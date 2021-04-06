package br.com.edu.zup.ecommerce.product.controller;

import br.com.edu.zup.ecommerce.product.model.Product;
import br.com.edu.zup.ecommerce.product.repository.ProductRepository;
import br.com.edu.zup.ecommerce.product.dto.ProductDetailResponse;
import br.com.edu.zup.ecommerce.review.repository.ReviewRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/products/detail")
public class ProductDetailController {

    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    public ProductDetailController(ProductRepository productRepository, ReviewRepository reviewRepository) {
        this.productRepository = productRepository;
        this.reviewRepository = reviewRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> productDetail(@PathVariable(value = "id",required = true) Long productId){

        Optional<Product> productOptional = productRepository.findById(productId);

        if (productOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }


        Product product = productOptional.get();


        return ResponseEntity.ok().body(new ProductDetailResponse(product,reviewRepository.getAverageReviewsNote()));
    }
}
