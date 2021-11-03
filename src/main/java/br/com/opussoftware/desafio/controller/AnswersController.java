package br.com.opussoftware.desafio.controller;

import br.com.opussoftware.desafio.controller.form.AnswerForm;
import br.com.opussoftware.desafio.model.Answer;
import br.com.opussoftware.desafio.model.Question;
import br.com.opussoftware.desafio.model.Status;
import br.com.opussoftware.desafio.repository.*;
import br.com.opussoftware.desafio.repository.AnswerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/answer")
public class AnswersController {
    //https://docs.spring.io/spring-framework/docs/4.2.x/spring-framework-reference/html/beans.html#beans-constructor-injection
    private final QuestionRepository questionRepository;
    private final AuthorRepository authorRepository;
    private final AnswerRepository answerRepository;

    public AnswersController(AnswerRepository answerRepository,
                               AuthorRepository authorRepository,
                             QuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.authorRepository = authorRepository;
        this.questionRepository = questionRepository;
    }

    @GetMapping
    public List<Answer> listByQuestion(@RequestParam Long questionId) {
        return answerRepository.findByQuestion_Id(questionId);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Answer> createAnswers(@RequestBody @Valid AnswerForm form, UriComponentsBuilder uriBuilder) {
        Answer answer = form.assemble(questionRepository, authorRepository);
        if (answer != null) {
            answerRepository.save(answer);

            Question question = answer.getQuestion();
            if (question.getStatus() == Status.NOT_ANSWERED) {
                question.setStatus(Status.ANSWERED);
                questionRepository.save(question);
            }

            URI uri = uriBuilder.path("/answers/{id}").buildAndExpand(answer.getId()).toUri();
            return ResponseEntity.created(uri).body(answer);
        }

        return ResponseEntity.badRequest().build();
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
