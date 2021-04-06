package br.com.edu.zup.ecommerce.user.model;

import br.com.edu.zup.ecommerce.auth.Role;
import br.com.edu.zup.ecommerce.user.util.Password;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
public class User implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String login;
    @Column(nullable = false)
    private String password;
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime registrationDate;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();

    /**
     * @deprecated (Hibernate only)
     */
    @Deprecated(forRemoval = false)
    public User() {
    }

    public User(@NotBlank String login,@NotNull String password) {
        this.login = login.toLowerCase();
        this.password = Password.build().encode(password);

    }

    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) && login.equals(user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login);
    }
}
