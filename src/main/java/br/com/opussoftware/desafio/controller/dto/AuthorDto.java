package br.com.opussoftware.desafio.controller.dto;

import br.com.opussoftware.desafio.model.Author;

public class AuthorDto {
    private Long id;
    private String name;
    private String email;

    AuthorDto(Author author) {
        this.id = author.getId();
        this.name = author.getName();
        this.email = author.getEmail();
    }
}
