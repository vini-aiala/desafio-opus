package br.com.opussoftware.desafio.controller.form;

import br.com.opussoftware.desafio.model.Author;
import br.com.opussoftware.desafio.model.Question;
import br.com.opussoftware.desafio.model.Subject;
import br.com.opussoftware.desafio.repository.QuestionRepository;
import br.com.opussoftware.desafio.repository.SubjectRepository;
import br.com.opussoftware.desafio.repository.AuthorRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class QuestionForm {
    @NotBlank
    private String title;
    @NotBlank
    private String text;
    @Positive
    private Long subjectId;
    @Positive
    private Long userId;

    public Question assemble(SubjectRepository subjectRepository, AuthorRepository authorRepository) {
        Subject subject = subjectRepository.getById(subjectId);
        Author author = authorRepository.getById(userId);
        return new Question(title, text, subject, author);
    }

    public Question update(Long id, QuestionRepository questionRepository) {
        Question question = questionRepository.getById(id);

        question.setTitle(title);
        question.setText(text);

        return question;
    }
}
