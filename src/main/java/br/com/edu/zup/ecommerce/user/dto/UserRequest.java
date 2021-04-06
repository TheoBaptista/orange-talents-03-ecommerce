package br.com.edu.zup.ecommerce.user.dto;

import br.com.edu.zup.ecommerce.shared.annotation.UniqueValueConstraint;
import br.com.edu.zup.ecommerce.user.model.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserRequest {

    private final @NotBlank @Email @UniqueValueConstraint(clazz = User.class,field = "login") String login;
    private final @NotBlank @Size(min = 6,message = "A senha deve ter no mínimo 6 dígitos") String password;

    public UserRequest(@NotBlank @Email String login, @NotBlank @Size(min = 6) String password) {
        this.login = login;
        this.password = password;
    }
    public User toUser(){
        return new User(this.login, this.password);
    }

}
