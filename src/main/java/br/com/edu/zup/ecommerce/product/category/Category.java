package br.com.edu.zup.ecommerce.product.category;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Category {

    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private @Column(unique = true, nullable = false) String name;
    private @ManyToOne Category parentCategory;

    /**
     * @deprecated (Hibernate only)
     */
    @Deprecated(since = "0.1")
    public Category() {
    }

    public Category(@NotBlank String name) {
        this.name = name;
    }

    public  void setParentCategory(Category parentCategory){
        this.parentCategory = parentCategory;
    }

    public String getName() {
        return name;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

}
