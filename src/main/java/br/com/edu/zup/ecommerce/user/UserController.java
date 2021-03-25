package br.com.edu.zup.ecommerce.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid UserRequest newUser){

        repository.save(newUser.toUser());
        return ResponseEntity.ok().build();
    }
}
