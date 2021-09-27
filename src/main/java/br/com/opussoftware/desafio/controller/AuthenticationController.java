package br.com.opussoftware.desafio.controller;

import br.com.opussoftware.desafio.controller.form.LoginForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @PostMapping
    public ResponseEntity<?> autenticate(@RequestBody @Valid LoginForm form) {
        return ResponseEntity.ok().build();

    }
}
