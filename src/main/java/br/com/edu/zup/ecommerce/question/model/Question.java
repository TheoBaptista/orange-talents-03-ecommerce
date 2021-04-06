package br.com.edu.zup.ecommerce.question.model;

import br.com.edu.zup.ecommerce.product.model.Product;
import br.com.edu.zup.ecommerce.user.model.User;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Question {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @Column(nullable = false) String title;
    private @CreationTimestamp LocalDate registrationDate;
    private @NotNull @ManyToOne @JoinColumn(nullable = false) User user;
    private @NotNull @ManyToOne @JoinColumn(nullable = false) Product product;

    /**
     * @deprecated (Hibernate only)
     */
    @Deprecated(forRemoval = false)
    public Question() {
    }

    public Question(String title, @NotNull User user, @NotNull Product product) {
        this.title = title;
        this.user = user;
        this.product = product;
    }

    public String getTitle() {
        return title;
    }

    public User getUser() {
        return user;
    }

    public Product getProduct() {
        return product;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

}
