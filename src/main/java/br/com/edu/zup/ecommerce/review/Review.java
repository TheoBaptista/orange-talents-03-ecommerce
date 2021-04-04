package br.com.edu.zup.ecommerce.review;

import br.com.edu.zup.ecommerce.product.Product;
import br.com.edu.zup.ecommerce.user.User;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Review {

    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false,length = 500)
    private String description;
    @Column(nullable = false) @Min(1) @Max(5)
    private Integer note;
    @JoinColumn(nullable = false) @ManyToOne @NotNull
    private Product product;
    @JoinColumn(nullable = false) @ManyToOne @NotNull
    private User user;

    /**
     * @deprecated (Hibernate only)
     */
    @Deprecated(forRemoval = false)
    public Review() {
    }

    public Review(String title, String description, @Min(1) @Max(5) Integer note, @NotNull Product product, @NotNull User user) {
        this.title = title;
        this.description = description;
        this.note = note;
        this.product = product;
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Integer getNote() {
        return note;
    }

    public Product getProduct() {
        return product;
    }

    public User getUser() {
        return user;
    }
}
