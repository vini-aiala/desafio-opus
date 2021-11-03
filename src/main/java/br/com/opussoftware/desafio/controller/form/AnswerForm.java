package br.com.opussoftware.desafio.controller.form;

import br.com.opussoftware.desafio.model.Answer;
import br.com.opussoftware.desafio.model.Author;
import br.com.opussoftware.desafio.model.Question;
import br.com.opussoftware.desafio.repository.AnswerRepository;
import br.com.opussoftware.desafio.repository.QuestionRepository;
import br.com.opussoftware.desafio.repository.AuthorRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class AnswerForm {
    @NotBlank
    private String text;
    @Positive
    private Long questionId;
    @Positive
    private Long authorId;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Answer assemble(QuestionRepository questionRepository, AuthorRepository authorRepository) {
        try {
            Question question = questionRepository.findById(questionId).orElseThrow(IllegalArgumentException::new);
            Author author = authorRepository.findById(authorId).orElseThrow(IllegalArgumentException::new);
            return new Answer(text, question, author);
        } catch (IllegalArgumentException e) {
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Answer update(Long id, AnswerRepository answerRepository) {
        Answer answer = answerRepository.getById(id);

        answer.setText(text);

        return answer;
    }
}
