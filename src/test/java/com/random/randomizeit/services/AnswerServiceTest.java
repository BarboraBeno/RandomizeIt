package com.random.randomizeit.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import com.random.randomizeit.exceptions.AnswerNotFoundException;
import com.random.randomizeit.exceptions.QuestionIsRequiredException;
import com.random.randomizeit.models.Answer;
import com.random.randomizeit.repositories.AnswerRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class AnswerServiceTest {

  private AnswerRepository fakeAnswerRepository;
  private AnswerService answerService;
  private Answer answer;
  private String question;

  @BeforeEach
  void beforeStart(){
    fakeAnswerRepository = Mockito.mock(AnswerRepository.class);
    answerService = new AnswerServiceImpl(fakeAnswerRepository);
    answer = new Answer(1L,"YES");
  }

  @Test
  void answerFound() {

    Mockito.when(fakeAnswerRepository.findById(answer.getId())).thenReturn(
        java.util.Optional.ofNullable(answer));

    assertEquals(1,answer.getId());
  }

  @Test
  void answerNotFound() {

    question = "How?";

    Mockito.when(fakeAnswerRepository.findById(any())).thenReturn(Optional.empty());

    assertThrows(AnswerNotFoundException.class,() -> answerService.answer(question));
  }

  @Test
  void questionIsEmpty() throws AnswerNotFoundException, QuestionIsRequiredException {

    question = "";

    Mockito.when(fakeAnswerRepository.findById(any())).thenReturn(
        java.util.Optional.ofNullable(answer));

    assertThrows(QuestionIsRequiredException.class,
        () -> answerService.answer(question));
  }

  @Test
  void answerSuccess() throws AnswerNotFoundException, QuestionIsRequiredException {
    question = "How?";

    Mockito.when(fakeAnswerRepository.findById(any())).thenReturn(
        java.util.Optional.ofNullable(answer));

    assertEquals("YES", answerService.answer(question));
  }

}