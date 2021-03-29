package br.com.edu.zup.ecommerce.user;

import org.springframework.security.crypto.password.PasswordEncoder;

public class Password {

    private final String password;

    public Password(String password, PasswordEncoder encoder) {
        this.password = encoder.encode(password);
    }

    public String getPassword() {
        return password;
    }
}
