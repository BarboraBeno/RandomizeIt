package com.random.randomizeit.services;

import com.random.randomizeit.exceptions.AnswerNotFoundException;

public interface AnswerService {

  String answer(String question) throws AnswerNotFoundException;

}
