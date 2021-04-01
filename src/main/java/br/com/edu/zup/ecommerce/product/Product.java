package br.com.edu.zup.ecommerce.product;

import br.com.edu.zup.ecommerce.category.Category;
import br.com.edu.zup.ecommerce.product.feature.ProductFeature;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Product {

    public @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    public @Column(nullable = false) String name;
    public @Column(nullable = false) @Positive @DecimalMin("10.00") BigDecimal price;
    public @Column(nullable = false) @Min(0) Integer quantity;
    public @Column(nullable = false) @Length(max = 10000) String description;
    public @Column(nullable = false) @CreationTimestamp LocalDate registerDate;
    @ManyToOne
    public @JoinColumn(nullable = false) Category category;
    @ManyToMany
    public @JoinColumn(nullable = false) List<ProductFeature> productFeatureList;

    /**
     * @deprecated (Hibernate only)
     */
    @Deprecated(forRemoval = true)
    public Product() {
    }

    public Product(String name, @Positive @DecimalMin("10.00") BigDecimal price, @Min(0) Integer quantity,
                   @Length(max = 10000) String description, Category category, List<ProductFeature> productFeatureList) {

        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.category = category;
        this.productFeatureList = productFeatureList;
    }



}
