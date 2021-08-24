package br.com.opussoftware.desafio.controller;

import br.com.opussoftware.desafio.controller.form.AnswerForm;
import br.com.opussoftware.desafio.model.Answer;
import br.com.opussoftware.desafio.model.Answer;
import br.com.opussoftware.desafio.repository.*;
import br.com.opussoftware.desafio.repository.AnswerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/answer")
public class AnswersController {
    //https://docs.spring.io/spring-framework/docs/4.2.x/spring-framework-reference/html/beans.html#beans-constructor-injection
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final AnswerRepository answerRepository;

    public AnswersController(AnswerRepository answerRepository,
                               UserRepository userRepository,
                             QuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }

    @GetMapping
    public Page<Answer> listByQuestion(@RequestParam Long questionId, Pageable pageable) {
        return answerRepository.findByQuestion_Id(questionId, pageable);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Answer> createAnswers(@Valid AnswerForm form, UriComponentsBuilder uriBuilder) {
        Answer answer = form.assemble(questionRepository, userRepository);
        answerRepository.save(answer);

        URI uri = uriBuilder.path("/answers/{id}").buildAndExpand(answer.getId()).toUri();
        return ResponseEntity.created(uri).body(answer);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Answer> update(@PathVariable Long id, @RequestBody @Valid AnswerForm form) {
        Optional<Answer> optional = answerRepository.findById(id);
        if (optional.isPresent()) {
            Answer answer = form.update(id, answerRepository);
            return ResponseEntity.ok(answer);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Answer> optional = answerRepository.findById(id);
        if (optional.isPresent()) {
            answerRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
