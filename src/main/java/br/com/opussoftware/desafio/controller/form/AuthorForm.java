package br.com.opussoftware.desafio.controller.form;

import br.com.opussoftware.desafio.model.Author;
import br.com.opussoftware.desafio.repository.AuthorRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;

public class AuthorForm {
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = encoder.encode(password);
    }

    public Author assemble() {
        return new Author(name, email, password);
    }

    public Author update(Long id, AuthorRepository authorRepository) {
        Author author = authorRepository.getById(id);

        author.setName(name);
        author.setEmail(email);
        author.setPassword(password);

        return author;
    }
}
