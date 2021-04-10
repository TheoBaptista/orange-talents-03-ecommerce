package br.com.edu.zup.ecommerce.auth;

import br.com.edu.zup.ecommerce.user.User;
import br.com.edu.zup.ecommerce.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Auth implements UserDetailsService {

    private final UserRepository repository;

    public Auth(UserRepository repository) {
        this.repository = repository;
    }

    @Override //make tests here
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> user = repository.findByLogin(login) ;
        if(user.isPresent()) {
            return user.get();
        }
        throw new UsernameNotFoundException("Invalid username or password!");
    }

}
