package br.com.opussoftware.desafio.controller;

import br.com.opussoftware.desafio.controller.dto.AuthorDto;
import br.com.opussoftware.desafio.controller.form.AuthorForm;
import br.com.opussoftware.desafio.model.Author;
import br.com.opussoftware.desafio.repository.AuthorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/author")
public class AuthorsController {
    private final AuthorRepository authorRepository;

    public AuthorsController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping(value = "/{id}")
    public AuthorDto recoverAuthor(@PathVariable String id) {
        Optional<Author> optional = authorRepository.findById(Long.parseLong(id));
        Author author = optional.orElse(null);
        if (author != null) {
            return new AuthorDto(author);
        }
        return null;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Author> createAuthor(@Valid AuthorForm form, UriComponentsBuilder uriBuilder) {
        Author author = form.assemble();
        authorRepository.save(author);

        URI uri = uriBuilder.path("/author/{id}").buildAndExpand(author.getId()).toUri();
        return ResponseEntity.created(uri).body(author);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Author> update(@PathVariable Long id, @RequestBody @Valid AuthorForm form) {
        Optional<Author> optional = authorRepository.findById(id);
        if (optional.isPresent()) {
            Author author = form.update(id, authorRepository);
            return ResponseEntity.ok(author);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Author> optional = authorRepository.findById(id);
        if (optional.isPresent()) {
            authorRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
