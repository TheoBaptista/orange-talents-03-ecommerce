package br.com.edu.zup.ecommerce.user.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Password {

    private final BCryptPasswordEncoder encoder;

    public Password() {
      this.encoder = new BCryptPasswordEncoder();
    }

    public static Password build() {
        return new Password();
    }

    public String encode(String password){
        return encoder.encode(password);
    }
}
