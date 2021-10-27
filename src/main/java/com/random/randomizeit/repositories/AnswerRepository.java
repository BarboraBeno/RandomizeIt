package com.random.randomizeit.repositories;

import com.random.randomizeit.models.Answer;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer,Long> {

  @NotNull Optional<Answer> findById(@NotNull Long id);

}
