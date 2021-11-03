package br.com.opussoftware.desafio.controller;

import br.com.opussoftware.desafio.config.security.JwtTokenService;
import br.com.opussoftware.desafio.controller.form.LoginForm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationManager authManager;
    private final JwtTokenService tokenService;

    public AuthenticationController(AuthenticationManager authManager, JwtTokenService tokenService) {
        this.authManager = authManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<?> authenticate(@RequestBody @Valid LoginForm form) {
        UsernamePasswordAuthenticationToken loginData = form.build();
        ObjectMapper mapper = new ObjectMapper();

        try {
            Authentication authentication = authManager.authenticate(loginData);
            String token = tokenService.generate(authentication);
            String jsonToken = mapper.writeValueAsString(token);
            return ResponseEntity.ok(jsonToken);
        } catch (AuthenticationException | JsonProcessingException e) {
            return ResponseEntity.badRequest().build();
        }


    }
}
