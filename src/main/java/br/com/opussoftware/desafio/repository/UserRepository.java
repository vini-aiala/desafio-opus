package br.com.opussoftware.desafio.repository;

import br.com.opussoftware.desafio.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
