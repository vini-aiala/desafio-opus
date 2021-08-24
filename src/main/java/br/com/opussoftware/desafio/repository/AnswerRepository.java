package br.com.opussoftware.desafio.repository;

import br.com.opussoftware.desafio.model.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Page<Answer> findByQuestion_Id(Long id, Pageable pageable);
}
