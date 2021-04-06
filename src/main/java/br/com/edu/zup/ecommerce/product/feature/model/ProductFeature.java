package br.com.edu.zup.ecommerce.product.feature.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ProductFeature {

    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private @Column(nullable = false) String name;
    private @Column(nullable = false) String description;

    /**
     * @deprecated (Hibernate only)
     */
    @Deprecated(forRemoval = false)
    public ProductFeature() {
    }

    public ProductFeature(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
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
