package com.organization.payment.v1.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.MessageFormat;
import java.util.UUID;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.organization.payment.commons.ControllerGenericTest;
import com.organization.payment.entity.BuyerEntity;
import com.organization.payment.factory.BuyerEntityFactory;
import com.organization.payment.factory.CreditCardRequestDtoFactory;
import com.organization.payment.repository.BuyerRepository;
import com.organization.payment.v1.dto.CreditCardRequestDto;

import lombok.SneakyThrows;

import org.apache.commons.lang3.StringUtils;
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
  public void postCreditCardWithInvalidCustomerId() {
    
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
  
  @Test
  @Rollback
  @SneakyThrows
  public void postCreditCardWithEmptyNumber() {
    final CreditCardRequestDto creditCardRequestDto = this.creditCardRequestDtoFactory.simple();
    creditCardRequestDto.setNumber(null);

    this.mockMvc.perform(
        post(String.format("%s/%s/creditcards", 
            this.uri, 
            UUID.randomUUID()))
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsBytes(creditCardRequestDto)))
        .andDo(print())
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status", equalTo(400)))
        .andExpect(jsonPath("$.errors[0]", equalTo("Please provide the number of CreditCard")))
        .andReturn()
        .getResponse()
        .getContentAsString();
  }
  
  @Test
  @Rollback
  @SneakyThrows
  public void postCreditCardWithEmptyHolderName() {
    final CreditCardRequestDto creditCardRequestDto = this.creditCardRequestDtoFactory.simple();
    creditCardRequestDto.setHolderName(null);

    this.mockMvc.perform(
        post(String.format("%s/%s/creditcards", 
            this.uri, 
            UUID.randomUUID()))
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsBytes(creditCardRequestDto)))
        .andDo(print())
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status", equalTo(400)))
        .andExpect(jsonPath("$.errors[0]", equalTo("Please provide the holder name of CreditCard")))
        .andReturn()
        .getResponse()
        .getContentAsString();
  }
  
  @Test
  @Rollback
  @SneakyThrows
  public void postCreditCardWithEmptyMonth() {
    final CreditCardRequestDto creditCardRequestDto = this.creditCardRequestDtoFactory.simple();
    creditCardRequestDto.setMonth(null);

    this.mockMvc.perform(
        post(String.format("%s/%s/creditcards", 
            this.uri, 
            UUID.randomUUID()))
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsBytes(creditCardRequestDto)))
        .andDo(print())
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status", equalTo(400)))
        .andExpect(jsonPath("$.errors[0]", equalTo("Please provide the month of CreditCard")))
        .andReturn()
        .getResponse()
        .getContentAsString();
  }
  
  @Test
  @Rollback
  @SneakyThrows
  public void postCreditCardWithEmptyYear() {
    final CreditCardRequestDto creditCardRequestDto = this.creditCardRequestDtoFactory.simple();
    creditCardRequestDto.setYear(null);

    this.mockMvc.perform(
        post(String.format("%s/%s/creditcards", 
            this.uri, 
            UUID.randomUUID()))
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsBytes(creditCardRequestDto)))
        .andDo(print())
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status", equalTo(400)))
        .andExpect(jsonPath("$.errors[0]", equalTo("Please provide the year of CreditCard")))
        .andReturn()
        .getResponse()
        .getContentAsString();
  }
  
  @Test
  @Rollback
  @SneakyThrows
  public void postCreditCardWithInvalidNumberLength() {
    final Integer length = CreditCardRequestDto.class.getDeclaredField("number").getAnnotation(Size.class).max();
    final String number = StringUtils.leftPad("", length + 1, '1');
    
    final CreditCardRequestDto creditCardRequestDto = this.creditCardRequestDtoFactory.simple();
    creditCardRequestDto.setNumber(number);

    this.mockMvc.perform(
        post(String.format("%s/%s/creditcards", 
            this.uri, 
            UUID.randomUUID()))
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsBytes(creditCardRequestDto)))
        .andDo(print())
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status", equalTo(400)))
        .andExpect(jsonPath("$.errors[0]", equalTo("size must be between 0 and " + length)))
        .andReturn()
        .getResponse()
        .getContentAsString();
  }
  
  @Test
  @Rollback
  @SneakyThrows
  public void postCreditCardWithInvalidHolderNameLength() {
    final Integer length = CreditCardRequestDto.class.getDeclaredField("holderName").getAnnotation(Size.class).max();
    final String holderName = StringUtils.leftPad("", length + 1, '1');
    
    final CreditCardRequestDto creditCardRequestDto = this.creditCardRequestDtoFactory.simple();
    creditCardRequestDto.setHolderName(holderName);

    this.mockMvc.perform(
        post(String.format("%s/%s/creditcards", 
            this.uri, 
            UUID.randomUUID()))
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsBytes(creditCardRequestDto)))
        .andDo(print())
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status", equalTo(400)))
        .andExpect(jsonPath("$.errors[0]", equalTo("size must be between 0 and " + length)))
        .andReturn()
        .getResponse()
        .getContentAsString();
  }
  
  @Test
  @Rollback
  @SneakyThrows
  public void postCreditCardWithMonthGreaterThanMaximum() {
    final Integer maxValue = (int)CreditCardRequestDto.class.getDeclaredField("month").getAnnotation(Max.class).value();
    final CreditCardRequestDto creditCardRequestDto = this.creditCardRequestDtoFactory.simple();
    creditCardRequestDto.setMonth(maxValue + 1);

    this.mockMvc.perform(
        post(String.format("%s/%s/creditcards", 
            this.uri, 
            UUID.randomUUID()))
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsBytes(creditCardRequestDto)))
        .andDo(print())
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status", equalTo(400)))
        .andExpect(jsonPath("$.errors[0]", equalTo("must be less than or equal to " + maxValue)))
        .andReturn()
        .getResponse()
        .getContentAsString();
  }
  
  @Test
  @Rollback
  @SneakyThrows
  public void postCreditCardWithMonthLessThanMinimum() {
    final Integer minValue = (int)CreditCardRequestDto.class.getDeclaredField("month").getAnnotation(Min.class).value();
    final CreditCardRequestDto creditCardRequestDto = this.creditCardRequestDtoFactory.simple();
    creditCardRequestDto.setMonth(minValue - 1);

    this.mockMvc.perform(
        post(String.format("%s/%s/creditcards", 
            this.uri, 
            UUID.randomUUID()))
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsBytes(creditCardRequestDto)))
        .andDo(print())
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status", equalTo(400)))
        .andExpect(jsonPath("$.errors[0]", equalTo("must be greater than or equal to " + minValue)))
        .andReturn()
        .getResponse()
        .getContentAsString();
  }
  
  @Test
  @Rollback
  @SneakyThrows
  public void postCreditCardWithYearLessThanMinimum() {
    final Integer minValue = (int)CreditCardRequestDto.class.getDeclaredField("year").getAnnotation(Min.class).value();
    final CreditCardRequestDto creditCardRequestDto = this.creditCardRequestDtoFactory.simple();
    creditCardRequestDto.setYear(minValue - 1);

    this.mockMvc.perform(
        post(String.format("%s/%s/creditcards", 
            this.uri, 
            UUID.randomUUID()))
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsBytes(creditCardRequestDto)))
        .andDo(print())
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status", equalTo(400)))
        .andExpect(jsonPath("$.errors[0]", equalTo("must be greater than or equal to " + minValue)))
        .andReturn()
        .getResponse()
        .getContentAsString();
  }
  
  @Test
  @Rollback
  @SneakyThrows
  public void postCreditCardWithInvalidNumber() {
    final String number = "1111111111111111";
    
    final CreditCardRequestDto creditCardRequestDto = this.creditCardRequestDtoFactory.simple();
    creditCardRequestDto.setNumber(number);

    this.mockMvc.perform(
        post(String.format("%s/%s/creditcards", 
            this.uri, 
            UUID.randomUUID()))
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsBytes(creditCardRequestDto)))
        .andDo(print())
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status", equalTo(400)))
        .andExpect(jsonPath("$.errors[0]", equalTo("Please provide a valid CreditCard")))
        .andReturn()
        .getResponse()
        .getContentAsString();
  }

}
