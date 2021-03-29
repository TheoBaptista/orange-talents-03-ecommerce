package br.com.edu.zup.ecommerce.auth;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    private String name;

    public Role(Long id) {
        this.id = id;

    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return name;
    }

    @Override
    public String getAuthority() {
        return this.name;
    }
}
