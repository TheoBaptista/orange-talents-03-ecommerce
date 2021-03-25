package br.com.edu.zup.ecommerce.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserRequest {

    private @NotBlank @Email String login;
    private @NotBlank @Size(min = 6,message = "A senha deve ter no mínimo 6 dígitos") String password;

    @Deprecated
    public UserRequest() {
    }

    public UserRequest(@NotBlank @Email String login, @NotBlank @Size(min = 6) String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public User toUser(){
        return new User(this.login,new BCryptEncoder(this.password));
    }

}
