package com.random.randomizeit.controllers;

import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.random.randomizeit.exceptions.AnswerNotFoundException;
import com.random.randomizeit.exceptions.QuestionIsRequiredException;
import com.random.randomizeit.models.Answer;
import com.random.randomizeit.models.QuestionDTO;
import com.random.randomizeit.repositories.AnswerRepository;
import com.random.randomizeit.services.AnswerService;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class mainIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private AnswerService answerService;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private AnswerRepository answerRepository;

  private Answer answer;

  @BeforeEach
  void beforeStart() {
  }

  @Test
  void askQuestionSuccess()
      throws Exception {
    QuestionDTO questionDTO = new QuestionDTO("How?");

    String question = questionDTO.getQuestion();

    Mockito.when(answerRepository.findById(any())).thenReturn(
        java.util.Optional.of(new Answer(1L, "YES")));

    mockMvc.perform(post("/ask").contentType("application/json")
        .content(objectMapper.writeValueAsString(question)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.question", is("How?")))
        .andExpect(jsonPath("$.answer",
            is(answerService.answer(question))))
        .andDo(print());
  }

  @Test
  void answerNotFound()
      throws Exception {
    QuestionDTO questionDTO = new QuestionDTO("How?");

    String question = questionDTO.getQuestion();

    Mockito.when(answerRepository.findById(any())).thenReturn(Optional.empty());

    mockMvc.perform(post("/ask").contentType("application/json")
        .content(objectMapper.writeValueAsString(question)))
        .andExpect(status().isNotFound())
        .andDo(print());
  }

  @Test
  void questionRequired()
      throws Exception {

    String question = "";

    Mockito.when(answerRepository.findById(any())).thenReturn(
        java.util.Optional.of(new Answer(1L, "YES")));

    mockMvc.perform(post("/ask").contentType("application/json")
        .content(objectMapper.writeValueAsString(question)))
        .andExpect(status().isBadRequest())
        .andDo(print());
  }
}