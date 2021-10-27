package com.random.randomizeit.services;

import com.random.randomizeit.exceptions.AnswerNotFoundException;
import com.random.randomizeit.exceptions.QuestionIsRequiredException;

public interface AnswerService {

  String answer(String question) throws AnswerNotFoundException, QuestionIsRequiredException;

}
