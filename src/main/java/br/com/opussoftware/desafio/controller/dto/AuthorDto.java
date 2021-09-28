package br.com.opussoftware.desafio.controller.dto;

import br.com.opussoftware.desafio.model.Author;

public class AuthorDto {
    private Long id;
    private String name;
    private String email;

    public AuthorDto(Author author) {
        this.id = author.getId();
        this.name = author.getName();
        this.email = author.getEmail();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
