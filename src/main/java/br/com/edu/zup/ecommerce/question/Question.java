package br.com.edu.zup.ecommerce.question;

import br.com.edu.zup.ecommerce.product.Product;
import br.com.edu.zup.ecommerce.shared.Action;
import br.com.edu.zup.ecommerce.user.User;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

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

    public void publish(List<Action> actionList){
        actionList.forEach(Action::execute);
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
