package br.com.opussoftware.desafio.model;

import javax.persistence.*;

@Entity
public class Subject {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@Enumerated(EnumType.STRING)
	private Category category;

	public Subject() {}

	public Subject(String name, String category) {
		this.name = name;
		this.category = Category.valueOf(category);
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(String category) {
		Category.valueOf(category);
	}

}
