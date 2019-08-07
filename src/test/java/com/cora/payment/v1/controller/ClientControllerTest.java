package com.cora.payment.v1.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.MessageFormat;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.cora.payment.commons.ControllerGenericTest;
import com.cora.payment.factory.ClientRequestDtoFactory;
import com.cora.payment.v1.dto.ClientRequestDto;

import lombok.SneakyThrows;

import org.junit.Before;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class ClientControllerTest extends ControllerGenericTest<ClientController> {

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Autowired
  private ClientRequestDtoFactory clientRequestDtoFactory;

  private MockMvc mockMvc;
  private String uri;

  public ClientControllerTest() {
  }

  @Before
  public void before() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    this.uri = MessageFormat.format("{0}{1}", PATH_RESOURCE, "clients");
  }

  @Test
  @Rollback
  @SneakyThrows
  public void postClientSuccess() {

    final ClientRequestDto clientRequestDto = this.clientRequestDtoFactory.simple();
        
    this.mockMvc.perform(
        post(this.uri)
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsBytes(clientRequestDto)))
        .andDo(print())
        .andExpect(status().isCreated())
        .andReturn()
        .getResponse()
        .getContentAsString();
  }
  
  @Test
  @Rollback
  @SneakyThrows
  public void postClientWithEmptyName() {
    final ClientRequestDto clientRequestDto = ClientRequestDto
        .builder()
        .build();

    this.mockMvc.perform(
        post(this.uri)
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsBytes(clientRequestDto)))
        .andDo(print())
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status", equalTo(400)))
        .andExpect(jsonPath("$.errors[0]", equalTo("Please provide the name of Client")))
        .andReturn()
        .getResponse()
        .getContentAsString();
  }

}
