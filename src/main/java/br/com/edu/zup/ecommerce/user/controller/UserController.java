package br.com.edu.zup.ecommerce.user.controller;

import br.com.edu.zup.ecommerce.user.dto.UserRequest;
import br.com.edu.zup.ecommerce.user.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;

    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> createUser(@RequestBody @Valid UserRequest newUser){

        repository.save(newUser.toUser());
        return ResponseEntity.ok().build();
    }
}
