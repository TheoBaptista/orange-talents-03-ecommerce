package br.com.edu.zup.ecommerce.user;

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

    @Deprecated
    public User() {
    }

    public User(@NotBlank String login,@NotNull PasswordEncoder encoder) {
        this.login = login;
        this.password = encoder.encode();
    }


    // apagar esses getters

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }
}
