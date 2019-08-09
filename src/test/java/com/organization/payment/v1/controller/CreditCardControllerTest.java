package com.organization.payment.v1.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.MessageFormat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.organization.payment.commons.ControllerGenericTest;
import com.organization.payment.entity.BuyerEntity;
import com.organization.payment.factory.BuyerEntityFactory;
import com.organization.payment.factory.CreditCardRequestDtoFactory;
import com.organization.payment.repository.BuyerRepository;
import com.organization.payment.v1.dto.CreditCardRequestDto;

import lombok.SneakyThrows;

import org.junit.Before;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class CreditCardControllerTest extends ControllerGenericTest<CreditCardController> {

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Autowired
  private CreditCardRequestDtoFactory creditCardRequestDtoFactory;
  
  @Autowired
  private BuyerEntityFactory buyerEntityFactory;
  
  @Autowired
  private BuyerRepository buyerRepository;
  
  private MockMvc mockMvc;
  private String uri;

  public CreditCardControllerTest() {
  }

  @Before
  public void setUp() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    this.uri = MessageFormat.format("{0}{1}", PATH_RESOURCE, "buyers");
  }
  
  
  @Test
  @Rollback
  @SneakyThrows
  public void postCreditCardSuccess() {
    BuyerEntity buyerEntity = this.buyerEntityFactory.simple();
    buyerEntity = this.buyerRepository.save(buyerEntity);

    final CreditCardRequestDto creditCardRequestDto = this.creditCardRequestDtoFactory.simple();
        
    this.mockMvc.perform(
        post(String.format("%s/%s/creditcards", 
            this.uri, 
            buyerEntity.getId()))
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsBytes(creditCardRequestDto)))
        .andDo(print())
        .andExpect(status().isCreated())
        .andReturn()
        .getResponse()
        .getContentAsString();
    
    //TODO: Add validation response object
  }
  
  @Test
  @Rollback
  @SneakyThrows
  public void postInvalidUUID() {
    
    this.mockMvc.perform(
        post(String.format("%s/%s/creditcards", 
            this.uri, 
            "invalid"))
        .contentType(MediaType.APPLICATION_JSON)
        .content(""))
        .andDo(print())
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status", equalTo(400)))
        .andExpect(jsonPath("$.errors[0]", equalTo("id should be of type java.util.UUID")))
        .andReturn()
        .getResponse()
        .getContentAsString();

  }

}
