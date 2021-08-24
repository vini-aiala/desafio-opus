package br.com.opussoftware.desafio.repository;

import br.com.opussoftware.desafio.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Author, Long> {
}
