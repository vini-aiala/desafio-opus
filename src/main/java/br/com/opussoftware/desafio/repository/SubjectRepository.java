package br.com.opussoftware.desafio.repository;

import br.com.opussoftware.desafio.model.Category;
import br.com.opussoftware.desafio.model.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findByCategory(Category category);
}
