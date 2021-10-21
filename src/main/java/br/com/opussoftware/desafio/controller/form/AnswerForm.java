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

    public Answer assemble(QuestionRepository questionRepository, AuthorRepository authorRepository) {
        Question question = questionRepository.getById(questionId);
        Author author = authorRepository.getById(authorId);
        return new Answer(text, question, author);
    }

    public Answer update(Long id, AnswerRepository answerRepository) {
        Answer answer = answerRepository.getById(id);

        answer.setText(text);

        return answer;
    }
}
