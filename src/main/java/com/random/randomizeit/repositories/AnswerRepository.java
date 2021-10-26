package com.random.randomizeit.repositories;

import com.random.randomizeit.models.Answer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer,Long> {

  Optional<Answer> findById(Long id);

}
