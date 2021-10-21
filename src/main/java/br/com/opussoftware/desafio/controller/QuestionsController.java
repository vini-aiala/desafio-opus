package br.com.opussoftware.desafio.controller;

import br.com.opussoftware.desafio.controller.form.QuestionForm;
import br.com.opussoftware.desafio.model.Question;
import br.com.opussoftware.desafio.repository.QuestionRepository;
import br.com.opussoftware.desafio.repository.SubjectRepository;
import br.com.opussoftware.desafio.repository.AuthorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/question")
public class QuestionsController {

    //https://docs.spring.io/spring-framework/docs/4.2.x/spring-framework-reference/html/beans.html#beans-constructor-injection
    private final QuestionRepository questionRepository;
    private final AuthorRepository authorRepository;
    private final SubjectRepository subjectRepository;

    public QuestionsController(QuestionRepository questionRepository,
                               AuthorRepository authorRepository,
                               SubjectRepository subjectRepository) {
        this.questionRepository = questionRepository;
        this.authorRepository = authorRepository;
        this.subjectRepository = subjectRepository;
    }

    @GetMapping
    public Page<Question> listBySubject(@RequestParam Long subjectId, Pageable pageable) {
        return questionRepository.findBySubject_Id(subjectId, pageable);
    }

    @GetMapping(value = "/{id}")
    public Question getById(@PathVariable String id) {
        Optional<Question> optional = questionRepository.findById(Long.parseLong(id));
        return optional.orElse(null);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Question> createQuestions(@RequestBody @Valid QuestionForm form, UriComponentsBuilder uriBuilder) {
        Question question = form.assemble(subjectRepository, authorRepository);
        if(question != null) {
            questionRepository.save(question);

            URI uri = uriBuilder.path("/questions/{id}").buildAndExpand(question.getId()).toUri();
            return ResponseEntity.created(uri).body(question);
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Question> update(@PathVariable Long id, @RequestBody @Valid QuestionForm form) {
        Optional<Question> optional = questionRepository.findById(id);
        if (optional.isPresent()) {
            Question question = form.update(id, questionRepository);
            return ResponseEntity.ok(question);
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Question> optional = questionRepository.findById(id);
        if (optional.isPresent()) {
            questionRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }
}
