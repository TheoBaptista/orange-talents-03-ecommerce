package br.com.edu.zup.ecommerce.user;

import br.com.edu.zup.ecommerce.shared.PasswordEncoder;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String login;
    @Column(nullable = false)
    private String password;
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime registrationDate;

    /**
     * @deprecated (Hibernate only)
     */
    @Deprecated(since = "0.1")
    public User() {
    }

    public User(@NotBlank String login,@NotNull PasswordEncoder encoder) {
        this.login = login;
        this.password = encoder.encode();
    }

}
