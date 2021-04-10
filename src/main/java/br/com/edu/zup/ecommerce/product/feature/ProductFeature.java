package br.com.edu.zup.ecommerce.product.feature;

import br.com.edu.zup.ecommerce.product.Product;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ProductFeature {

    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private @Column(nullable = false) String name;
    private @Column(nullable = false) String description;
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    /**
     * @deprecated (Hibernate only)
     */
    @Deprecated(forRemoval = false)
    public ProductFeature() {
    }

    public ProductFeature(String name, String description, Product product) {
        this.name = name;
        this.description = description;
        this.product = product;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Product getProduct() {
        return product;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductFeature that = (ProductFeature) o;
        return id.equals(that.id) && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
