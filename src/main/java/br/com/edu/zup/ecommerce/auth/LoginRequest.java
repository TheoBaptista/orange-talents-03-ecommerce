package br.com.edu.zup.ecommerce.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank
    private final String login;
    @NotBlank
    private final String password;

    public LoginRequest(@NotBlank String login, @NotBlank String password) {
        this.login = login;
        this.password = password;
    }

    public UsernamePasswordAuthenticationToken converterTo() {
        return new UsernamePasswordAuthenticationToken(login, password);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
