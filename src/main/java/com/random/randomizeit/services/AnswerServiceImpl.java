package com.random.randomizeit.services;

import com.random.randomizeit.exceptions.AnswerNotFoundException;
import com.random.randomizeit.exceptions.QuestionIsRequiredException;
import com.random.randomizeit.models.Answer;
import com.random.randomizeit.repositories.AnswerRepository;
import java.util.Optional;
import java.util.Random;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements AnswerService{

  private final AnswerRepository answerRepository;

  public AnswerServiceImpl(AnswerRepository answerRepository) {
    this.answerRepository = answerRepository;
  }

  @Override
  public String answer(String question) throws AnswerNotFoundException, QuestionIsRequiredException {

    Random random = new Random();
    Long n = (long) random.nextInt(10);

    Optional<Answer> optionalAnswer = answerRepository.findById(n);

    if(optionalAnswer.isEmpty()){
      throw new AnswerNotFoundException("Answer wasnt found.");
    }

    if(question.equals("") || question == null){
      throw  new QuestionIsRequiredException("Question must not be empty.");
    }

    return optionalAnswer.get().getAnswer();
  }
}
