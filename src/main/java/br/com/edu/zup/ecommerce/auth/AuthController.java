package br.com.edu.zup.ecommerce.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authManager, TokenService tokenService) {
        this.authManager = authManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<?> authenticate(@RequestBody @Valid LoginRequest loginRequest){

        UsernamePasswordAuthenticationToken loginData = loginRequest.converterTo();

        try {
            Authentication auth = authManager.authenticate(loginData);
            String token = tokenService.generateToken(auth);
            return ResponseEntity.ok(new TokenResponse(token));


        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }

}
