package br.com.edu.zup.ecommerce.product;

import br.com.edu.zup.ecommerce.product.category.Category;
import br.com.edu.zup.ecommerce.product.category.CategoryRepository;
import br.com.edu.zup.ecommerce.product.feature.ProductFeature;
import br.com.edu.zup.ecommerce.product.feature.ProductFeatureRepository;
import br.com.edu.zup.ecommerce.product.feature.UniqueFeatureNameConstraintValidator;
import br.com.edu.zup.ecommerce.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {
    //4
    private final UniqueFeatureNameConstraintValidator uniqueFeatureNameConstraintValidator;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ProductFeatureRepository productFeatureRepository;


    public ProductController(UniqueFeatureNameConstraintValidator uniqueFeatureNameConstraintValidator,
                             CategoryRepository categoryRepository, ProductRepository productRepository,
                             ProductFeatureRepository productFeatureRepository) {
        this.uniqueFeatureNameConstraintValidator = uniqueFeatureNameConstraintValidator;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.productFeatureRepository = productFeatureRepository;
    }

    @InitBinder //1
    public void init(WebDataBinder webDataBinder){
        webDataBinder.addValidators(uniqueFeatureNameConstraintValidator);
    }

    @PostMapping
    @Transactional //1
    public ResponseEntity<?> createProduct(@RequestBody @Valid ProductRequest newProduct, @AuthenticationPrincipal User user){

        Category category = categoryRepository.findByName(newProduct.getCategoryName());
        Product product = newProduct.toProduct(category,user);
        List<ProductFeature> productFeatures = newProduct.toProductFeatureList(product);

        productFeatureRepository.saveAll(productFeatures);
        productRepository.save(product);

        return ResponseEntity.ok().build();
    }

}
