package br.com.opussoftware.desafio.controller.form;

import br.com.opussoftware.desafio.model.Category;
import br.com.opussoftware.desafio.model.Subject;
import br.com.opussoftware.desafio.repository.SubjectRepository;

import javax.validation.constraints.NotBlank;


public class SubjectForm {
    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @NotBlank
    private String categoryName;

    public Subject assemble() {
        return new Subject(name, categoryName);
    }

    public Subject update(Long id, SubjectRepository subjectRepository) {
        Subject subject = subjectRepository.getById(id);

        subject.setName(this.name);
        subject.setCategory(this.categoryName);

        return subject;
    }
}
