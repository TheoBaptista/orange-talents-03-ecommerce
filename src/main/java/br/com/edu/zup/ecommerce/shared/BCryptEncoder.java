package br.com.edu.zup.ecommerce.shared;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptEncoder implements PasswordEncoder {

    private final String password;

    public BCryptEncoder(String password) {
        this.password = password;
    }

    @Override
    public String encode() {
        return new BCryptPasswordEncoder().encode(this.password);
    }

    @Override
    public PasswordEncoder build(String password) {
        return new BCryptEncoder(password);
    }
}
