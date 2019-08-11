package com.organization.payment.v1.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.MessageFormat;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.organization.payment.commons.ControllerGenericTest;
import com.organization.payment.factory.BuyerRequestDtoFactory;
import com.organization.payment.v1.controller.BuyerController;
import com.organization.payment.v1.dto.BuyerRequestDto;

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

public class BuyerControllerTest extends ControllerGenericTest<BuyerController> {

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Autowired
  private BuyerRequestDtoFactory buyerRequestDtoFactory;

  private MockMvc mockMvc;
  private String uri;

  public BuyerControllerTest() {
  }

  @Before
  public void before() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    this.uri = MessageFormat.format("{0}{1}", PATH_RESOURCE, "buyers");
  }

  @Test
  @Rollback
  @SneakyThrows
  public void postBuyerSuccess() {

    final BuyerRequestDto buyerRequestDto = this.buyerRequestDtoFactory.simple();
        
    this.mockMvc.perform(
        post(this.uri)
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsBytes(buyerRequestDto)))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").exists())
        .andExpect(jsonPath("$.cpf").value(buyerRequestDto.getCpf()))
        .andExpect(jsonPath("$.email").value(buyerRequestDto.getEmail()))
        .andExpect(jsonPath("$.name").value(buyerRequestDto.getName()))
        .andReturn()
        .getResponse()
        .getContentAsString();
  }
  
  @Test
  @Rollback
  @SneakyThrows
  public void postBuyerWithEmptyName() {
    final BuyerRequestDto buyerRequestDto = this.buyerRequestDtoFactory.simple();
    buyerRequestDto.setName(null);

    this.mockMvc.perform(
        post(this.uri)
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsBytes(buyerRequestDto)))
        .andDo(print())
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status", equalTo(400)))
        .andExpect(jsonPath("$.errors[0]", equalTo("Please provide the name of Buyer")))
        .andReturn()
        .getResponse()
        .getContentAsString();
  }
  
  @Test
  @Rollback
  @SneakyThrows
  public void postBuyerWithEmptyEmail() {
    final BuyerRequestDto buyerRequestDto = this.buyerRequestDtoFactory.simple();
    buyerRequestDto.setEmail(null);

    this.mockMvc.perform(
        post(this.uri)
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsBytes(buyerRequestDto)))
        .andDo(print())
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status", equalTo(400)))
        .andExpect(jsonPath("$.errors[0]", equalTo("Please provide the email of Buyer")))
        .andReturn()
        .getResponse()
        .getContentAsString();
  }
  
  @Test
  @Rollback
  @SneakyThrows
  public void postBuyerWithEmptyCpf() {
    final BuyerRequestDto buyerRequestDto = this.buyerRequestDtoFactory.simple();
    buyerRequestDto.setCpf(null);

    this.mockMvc.perform(
        post(this.uri)
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsBytes(buyerRequestDto)))
        .andDo(print())
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status", equalTo(400)))
        .andExpect(jsonPath("$.errors[0]", equalTo("Please provide the cpf of Buyer")))
        .andReturn()
        .getResponse()
        .getContentAsString();
  }
  
  @Test
  @Rollback
  @SneakyThrows
  public void postBuyerWithInvalidNameLength() {
    final Integer length = BuyerRequestDto.class.getDeclaredField("name").getAnnotation(Size.class).max();
    final String name = StringUtils.leftPad("", length + 1, 'A');
    
    final BuyerRequestDto buyerRequestDto = this.buyerRequestDtoFactory.simple();
    buyerRequestDto.setName(name);

    this.mockMvc.perform(
        post(this.uri)
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsBytes(buyerRequestDto)))
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
  public void postBuyerWithInvalidCpfLength() {
    final String cpf = "123123123123";
    final BuyerRequestDto buyerRequestDto = this.buyerRequestDtoFactory.simple();
    buyerRequestDto.setCpf(cpf);

    this.mockMvc.perform(
        post(this.uri)
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsBytes(buyerRequestDto)))
        .andDo(print())
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status", equalTo(400)))
        .andExpect(jsonPath("$.errors[0]", equalTo("cpf entered "
            + "[" + cpf + "] is invalid. "
            + "It must be between 11 and 11")))
        .andReturn()
        .getResponse()
        .getContentAsString();
  }
  
  @Test
  @Rollback
  @SneakyThrows
  public void postBuyerWithInvalidEmail() {
    final String email = "email";
    
    final BuyerRequestDto buyerRequestDto = this.buyerRequestDtoFactory.simple();
    buyerRequestDto.setEmail(email);

    this.mockMvc.perform(
        post(this.uri)
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsBytes(buyerRequestDto)))
        .andDo(print())
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status", equalTo(400)))
        .andExpect(jsonPath("$.errors[0]", equalTo("Please provide a valid email of Buyer")))
        .andReturn()
        .getResponse()
        .getContentAsString();
  }
  
  @Test
  @Rollback
  @SneakyThrows
  public void postBuyerWithInvalidCpf() {
    final String cpf = "11111111111";
    final BuyerRequestDto buyerRequestDto = this.buyerRequestDtoFactory.simple();
    buyerRequestDto.setCpf(cpf);

    this.mockMvc.perform(
        post(this.uri)
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsBytes(buyerRequestDto)))
        .andDo(print())
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status", equalTo(400)))
        .andExpect(jsonPath("$.errors[0]", equalTo("Please provide a valid cpf to the Buyer")))
        .andReturn()
        .getResponse()
        .getContentAsString();
  }

}
