package br.com.opussoftware.desafio.controller;

import br.com.opussoftware.desafio.controller.form.SubjectForm;
import br.com.opussoftware.desafio.model.Category;
import br.com.opussoftware.desafio.model.Subject;
import br.com.opussoftware.desafio.repository.SubjectRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subject")
public class SubjectsController {

    //https://docs.spring.io/spring-framework/docs/4.2.x/spring-framework-reference/html/beans.html#beans-constructor-injection
    private final SubjectRepository subjectRepository;

    public SubjectsController(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @GetMapping
    public List<Subject> recoverSubjects(@RequestParam(required = false) String category) {
        if (category == null) {
            return subjectRepository.findAll();
        } else {
            return subjectRepository.findByCategory(Category.valueOf(category));
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Subject> createSubjects(@RequestBody @Valid SubjectForm form, UriComponentsBuilder uriBuilder) {
        Subject subject = form.assemble();
        subjectRepository.save(subject);

        URI uri = uriBuilder.path("/subjects/{id}").buildAndExpand(subject.getId()).toUri();
        return ResponseEntity.created(uri).body(subject);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Subject> update(@PathVariable Long id, @RequestBody @Valid SubjectForm form) {
        Optional<Subject> optional = subjectRepository.findById(id);
        if (optional.isPresent()) {
            Subject subject = form.update(id, subjectRepository);
            return ResponseEntity.ok(subject);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Subject> optional = subjectRepository.findById(id);
        if (optional.isPresent()) {
            subjectRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
