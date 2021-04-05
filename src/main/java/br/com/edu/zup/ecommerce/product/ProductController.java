package br.com.edu.zup.ecommerce.product;

import br.com.edu.zup.ecommerce.category.Category;
import br.com.edu.zup.ecommerce.category.CategoryRepository;
import br.com.edu.zup.ecommerce.product.feature.ProductFeature;
import br.com.edu.zup.ecommerce.product.feature.ProductFeatureRepository;
import br.com.edu.zup.ecommerce.product.feature.UniqueFeatureNameConstraintValidator;
import br.com.edu.zup.ecommerce.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {

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

    @InitBinder
    public void init(WebDataBinder webDataBinder){
        webDataBinder.addValidators(uniqueFeatureNameConstraintValidator);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> createProduct(@RequestBody @Valid ProductRequest newProduct){

        List<ProductFeature> productsFeatures = newProduct.getProductsFeatures();
        Category category = categoryRepository.findByName(newProduct.getCategoryName());

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Product product = newProduct.toProduct(category,productsFeatures,user);

        productFeatureRepository.saveAll(productsFeatures);
        productRepository.save(product);

        return ResponseEntity.ok().build();
    }

}
