package br.com.opussoftware.desafio.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Answer {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String text;

	@ManyToOne
	private Question question;
	@ManyToOne
	private Author author;

	public Answer(String text, Question question, Author author) {
		this.text = text;
		this.question = question;
		this.author = author;
	}

	public Answer() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}
}
