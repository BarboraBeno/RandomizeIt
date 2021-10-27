package com.random.randomizeit.controllers;

import com.random.randomizeit.exceptions.AnswerNotFoundException;
import com.random.randomizeit.exceptions.QuestionIsRequiredException;
import com.random.randomizeit.services.AnswerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class main {

  private final AnswerService answerService;

  public main(AnswerService answerService) {
    this.answerService = answerService;
  }

  @GetMapping("/ask")
  public String askMe(){
    return "index";
  }

  @GetMapping("/answer")
  public String answer(){
    return "answer";
  }

  @PostMapping("/ask")
  public String answer(Model model,@RequestParam(name="question") String question)
      throws AnswerNotFoundException, QuestionIsRequiredException {
    model.addAttribute("answer", answerService.answer(question));
    return "redirect:/answer";
  }

}
