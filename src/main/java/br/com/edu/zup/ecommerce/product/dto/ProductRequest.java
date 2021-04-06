package br.com.edu.zup.ecommerce.product.dto;

import br.com.edu.zup.ecommerce.category.model.Category;
import br.com.edu.zup.ecommerce.product.model.Product;
import br.com.edu.zup.ecommerce.product.feature.model.ProductFeature;
import br.com.edu.zup.ecommerce.product.feature.dto.ProductFeatureRequest;
import br.com.edu.zup.ecommerce.shared.annotation.OnlyCreateIfExist;
import br.com.edu.zup.ecommerce.user.model.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductRequest {

    private final @NotBlank String name;
    private final @NotNull @DecimalMin("10.00") @Positive BigDecimal price;
    private final @NotNull @Min(0) @PositiveOrZero  Integer quantity;
    private final @NotBlank @Length(max = 1000) String description;
    private final @NotBlank @OnlyCreateIfExist(clazz = Category.class,field = "name") String categoryName;
    private final @Valid @NotNull @Size(min = 3) List<ProductFeatureRequest> productFeatureRequestList;

    public ProductRequest(@NotBlank String name, @NotNull @DecimalMin("10.00") @Positive BigDecimal price,
                          @NotNull @Min(0) Integer quantity, @NotBlank @Length(max = 1000) String description,
                          @NotBlank String categoryName, @Valid @NotNull @Size(min = 3) List<ProductFeatureRequest> productFeatureRequestList) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.categoryName = categoryName;
        this.productFeatureRequestList = productFeatureRequestList;
    }

    public List<ProductFeatureRequest> getProductFeatureList() {
        return productFeatureRequestList;
    }

    //make tests here
    public Boolean haveEqualsFeatureName(){
       Set<String> featureNames = new HashSet<>();
       return !productFeatureRequestList.stream().filter(f -> !featureNames.add(f.getFeatureName().toLowerCase())).collect(Collectors.toSet()).isEmpty();
    }

    public String getName() {
        return name;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public List<ProductFeature> getProductsFeatures(){
        return productFeatureRequestList.stream().map(ProductFeatureRequest::toProductFeature).collect(Collectors.toList());
    }

    public Product toProduct(Category category, List<ProductFeature> productFeatureList, User user){
      return new Product(this.name,this.price,this.quantity,this.description,category,productFeatureList,user);
    }

}
