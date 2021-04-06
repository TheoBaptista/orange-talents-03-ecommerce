package br.com.edu.zup.ecommerce.question.repository;

import br.com.edu.zup.ecommerce.question.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
}
