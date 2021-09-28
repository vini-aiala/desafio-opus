package br.com.opussoftware.desafio.controller.form;

import br.com.opussoftware.desafio.model.Answer;
import br.com.opussoftware.desafio.model.Author;
import br.com.opussoftware.desafio.model.Question;
import br.com.opussoftware.desafio.repository.AnswerRepository;
import br.com.opussoftware.desafio.repository.AuthorRepository;
import br.com.opussoftware.desafio.repository.QuestionRepository;

public class AuthorForm {
    private String name;
    private String email;
    private String password;

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
