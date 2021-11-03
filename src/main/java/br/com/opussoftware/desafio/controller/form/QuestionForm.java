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
        try {
            Subject subject = subjectRepository.findById(subjectId).orElseThrow(IllegalArgumentException::new);
            Author author = authorRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
            return new Question(title, text, subject, author);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Question update(Long id, QuestionRepository questionRepository) {
        Question question = questionRepository.getById(id);

        question.setTitle(title);
        question.setText(text);

        return question;
    }
}
