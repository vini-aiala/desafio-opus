package br.com.opussoftware.desafio.repository;

import br.com.opussoftware.desafio.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Page<Question> findBySubject_Id(Long id, Pageable pageable);
}
