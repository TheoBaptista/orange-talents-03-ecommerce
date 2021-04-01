package br.com.edu.zup.ecommerce.product.feature;

import javax.persistence.*;

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


}
