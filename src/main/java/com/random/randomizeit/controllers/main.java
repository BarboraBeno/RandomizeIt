package com.random.randomizeit.controllers;

import com.random.randomizeit.exceptions.AnswerNotFoundException;
import com.random.randomizeit.exceptions.QuestionIsRequiredException;
import com.random.randomizeit.models.Answer;
import com.random.randomizeit.models.AnswerDTO;
import com.random.randomizeit.models.QuestionDTO;
import com.random.randomizeit.services.AnswerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class main {

  private final AnswerService answerService;

  public main(AnswerService answerService) {
    this.answerService = answerService;
  }

  @PostMapping("/ask")
  public ResponseEntity askQuestion(@RequestBody QuestionDTO question)
      throws AnswerNotFoundException, QuestionIsRequiredException {
    return ResponseEntity.status(HttpStatus.OK).body(new AnswerDTO(question.getQuestion(),
        answerService.answer(question.getQuestion())));
  }
}
