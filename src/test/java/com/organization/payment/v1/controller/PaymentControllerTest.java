package com.organization.payment.v1.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.util.UUID;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.organization.payment.commons.ControllerGenericTest;
import com.organization.payment.entity.BuyerEntity;
import com.organization.payment.entity.ClientEntity;
import com.organization.payment.entity.CreditCardEntity;
import com.organization.payment.entity.PaymentEntity;
import com.organization.payment.factory.BuyerEntityFactory;
import com.organization.payment.factory.ClientEntityFactory;
import com.organization.payment.factory.CreditCardEntityFactory;
import com.organization.payment.factory.PaymentEntityFactory;
import com.organization.payment.factory.PaymentRequestDtoFactory;
import com.organization.payment.repository.BuyerRepository;
import com.organization.payment.repository.ClientRepository;
import com.organization.payment.repository.CreditCardRepository;
import com.organization.payment.repository.PaymentRepository;
import com.organization.payment.v1.dto.PaymentBuyerRequestDto;
import com.organization.payment.v1.dto.PaymentClientRequestDto;
import com.organization.payment.v1.dto.PaymentCreditCardRequestDto;
import com.organization.payment.v1.dto.PaymentRequestDto;
import com.organization.payment.v1.dto.PaymentResponseDto;

import lombok.SneakyThrows;

import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class PaymentControllerTest extends ControllerGenericTest<PaymentController> {

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Autowired
  private PaymentRequestDtoFactory paymentRequestDtoFactory;
  
  @Autowired
  private BuyerEntityFactory buyerEntityFactory;
  
  @Autowired
  private ClientEntityFactory clientEntityFactory;
  
  @Autowired
  private CreditCardEntityFactory creditCardEntityFactory;
  
  @Autowired
  private PaymentEntityFactory paymentEntityFactory;
  
  @Autowired
  private BuyerRepository buyerRepository;
  
  @Autowired
  private ClientRepository clientRepository;
  
  @Autowired
  private CreditCardRepository creditCardRepository;
  
  @Autowired
  private PaymentRepository paymentRepository;
  
  @Autowired
  private ModelMapper modelMapper;
  
  private MockMvc mockMvc;
  private String uri;

  public PaymentControllerTest() {
  }

  @Before
  public void setUp() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    this.uri = MessageFormat.format("{0}{1}", PATH_RESOURCE, "payments");
  }
  
  
  @Test
  @Rollback
  @SneakyThrows
  public void postPaymentCreditCardSuccess() {
    BuyerEntity buyerEntity = this.buyerEntityFactory.simple();
    buyerEntity = this.buyerRepository.save(buyerEntity);
    
    ClientEntity clientEntity = this.clientEntityFactory.simple();
    clientEntity = this.clientRepository.save(clientEntity);
    
    CreditCardEntity creditCardEntity = this.creditCardEntityFactory.simple();
    creditCardEntity.setBuyer(buyerEntity);
    creditCardEntity = this.creditCardRepository.save(creditCardEntity);

    final PaymentRequestDto paymentRequestDto = this.paymentRequestDtoFactory.simpleTypeCreditCard();
    
    paymentRequestDto.setBuyer(this.modelMapper.map(
        buyerEntity, PaymentBuyerRequestDto.class));
    
    paymentRequestDto.setClient(this.modelMapper.map(
        clientEntity, PaymentClientRequestDto.class));
    
    paymentRequestDto.setCreditCard(this.modelMapper.map(
        creditCardEntity, PaymentCreditCardRequestDto.class));
    
    this.mockMvc.perform(
        post(this.uri)
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsBytes(paymentRequestDto)))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").exists())
        .andExpect(jsonPath("$.status").exists())
        .andExpect(jsonPath("$.barcodeNumber").doesNotExist())
        .andExpect(jsonPath("$.amount").value(paymentRequestDto.getAmount()))
        .andExpect(jsonPath("$.type").value(paymentRequestDto.getType().getText()))
        .andExpect(jsonPath("$.client.id").value(paymentRequestDto.getClient().getId().toString()))
        .andExpect(jsonPath("$.buyer.id").value(paymentRequestDto.getBuyer().getId().toString()))
        .andExpect(jsonPath("$.creditCard.id").value(paymentRequestDto.getCreditCard().getId().toString()))
        .andReturn()
        .getResponse()
        .getContentAsString();
    
  }
  
  @Test
  @Rollback
  @SneakyThrows
  public void postPaymentBankSlipSuccess() {
    BuyerEntity buyerEntity = this.buyerEntityFactory.simple();
    buyerEntity = this.buyerRepository.save(buyerEntity);
    
    ClientEntity clientEntity = this.clientEntityFactory.simple();
    clientEntity = this.clientRepository.save(clientEntity);
    
    final PaymentRequestDto paymentRequestDto = this.paymentRequestDtoFactory.simpleTypeBankSlip();
    
    paymentRequestDto.setBuyer(this.modelMapper.map(
        buyerEntity, PaymentBuyerRequestDto.class));
    
    paymentRequestDto.setClient(this.modelMapper.map(
        clientEntity, PaymentClientRequestDto.class));
    
    this.mockMvc.perform(
        post(this.uri)
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsBytes(paymentRequestDto)))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").exists())
        .andExpect(jsonPath("$.status").doesNotExist())
        .andExpect(jsonPath("$.barcodeNumber").exists())
        .andExpect(jsonPath("$.amount").value(paymentRequestDto.getAmount()))
        .andExpect(jsonPath("$.type").value(paymentRequestDto.getType().getText()))
        .andExpect(jsonPath("$.client.id").value(paymentRequestDto.getClient().getId().toString()))
        .andExpect(jsonPath("$.buyer.id").value(paymentRequestDto.getBuyer().getId().toString()))
        .andExpect(jsonPath("$.creditCard").doesNotExist())
        .andReturn()
        .getResponse()
        .getContentAsString();
    
  }
  
  @Test
  @Rollback
  @SneakyThrows
  public void postPaymentWithNullAmount() {
    
    final PaymentRequestDto paymentRequestDto = this.paymentRequestDtoFactory.simpleTypeBankSlip();
    paymentRequestDto.setAmount(null);
    
    this.mockMvc.perform(
        post(this.uri)
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsBytes(paymentRequestDto)))
        .andDo(print())
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status", equalTo(400)))
        .andExpect(jsonPath("$.errors[0]", equalTo("Please provide the amount of Payment")))
        .andReturn()
        .getResponse()
        .getContentAsString();

  }
  
  @Test
  @Rollback
  @SneakyThrows
  public void postPaymentWithNullType() {
    
    final PaymentRequestDto paymentRequestDto = this.paymentRequestDtoFactory.simpleTypeBankSlip();
    paymentRequestDto.setType(null);
    
    this.mockMvc.perform(
        post(this.uri)
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsBytes(paymentRequestDto)))
        .andDo(print())
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status", equalTo(400)))
        .andExpect(jsonPath("$.errors[0]", equalTo("Please provide the payment type of Payment")))
        .andReturn()
        .getResponse()
        .getContentAsString();

  }
  
  @Test
  @Rollback
  @SneakyThrows
  public void postPaymentWithNullClient() {
    
    final PaymentRequestDto paymentRequestDto = this.paymentRequestDtoFactory.simpleTypeBankSlip();
    paymentRequestDto.setClient(null);
    
    this.mockMvc.perform(
        post(this.uri)
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsBytes(paymentRequestDto)))
        .andDo(print())
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status", equalTo(400)))
        .andExpect(jsonPath("$.errors[0]", equalTo("Please provide the client of Payment")))
        .andReturn()
        .getResponse()
        .getContentAsString();

  }
  
  @Test
  @Rollback
  @SneakyThrows
  public void postPaymentWithNullClientId() {
    
    final PaymentRequestDto paymentRequestDto = this.paymentRequestDtoFactory.simpleTypeBankSlip();
    paymentRequestDto.getClient().setId(null);
    
    this.mockMvc.perform(
        post(this.uri)
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsBytes(paymentRequestDto)))
        .andDo(print())
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status", equalTo(400)))
        .andExpect(jsonPath("$.errors[0]", equalTo("Please provide the client id of Payment")))
        .andReturn()
        .getResponse()
        .getContentAsString();

  }
  
  @Test
  @Rollback
  @SneakyThrows
  public void postPaymentWithNullBuyer() {
    
    final PaymentRequestDto paymentRequestDto = this.paymentRequestDtoFactory.simpleTypeBankSlip();
    paymentRequestDto.setBuyer(null);
    
    this.mockMvc.perform(
        post(this.uri)
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsBytes(paymentRequestDto)))
        .andDo(print())
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status", equalTo(400)))
        .andExpect(jsonPath("$.errors[0]", equalTo("Please provide the buyer of Payment")))
        .andReturn()
        .getResponse()
        .getContentAsString();

  }
  
  @Test
  @Rollback
  @SneakyThrows
  public void postPaymentWithNullBuyerId() {
    
    final PaymentRequestDto paymentRequestDto = this.paymentRequestDtoFactory.simpleTypeBankSlip();
    paymentRequestDto.getBuyer().setId(null);
    
    this.mockMvc.perform(
        post(this.uri)
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsBytes(paymentRequestDto)))
        .andDo(print())
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status", equalTo(400)))
        .andExpect(jsonPath("$.errors[0]", equalTo("Please provide the buyer id of Payment")))
        .andReturn()
        .getResponse()
        .getContentAsString();

  }
  
  @Test
  @Rollback
  @SneakyThrows
  public void postPaymentWithNullCreditCard() {
    
    final PaymentRequestDto paymentRequestDto = this.paymentRequestDtoFactory.simpleTypeCreditCard();
    paymentRequestDto.setCreditCard(null);
    
    this.mockMvc.perform(
        post(this.uri)
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsBytes(paymentRequestDto)))
        .andDo(print())
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status", equalTo(400)))
        .andExpect(jsonPath("$.errors[0]", equalTo("Field creditCard is required when type is CREDIT_CARD")))
        .andReturn()
        .getResponse()
        .getContentAsString();

  }
  
  @Test
  @Rollback
  @SneakyThrows
  public void postPaymentWithNullCreditCardId() {
    
    final PaymentRequestDto paymentRequestDto = this.paymentRequestDtoFactory.simpleTypeCreditCard();
    paymentRequestDto.getCreditCard().setId(null);
    
    this.mockMvc.perform(
        post(this.uri)
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsBytes(paymentRequestDto)))
        .andDo(print())
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status", equalTo(400)))
        .andExpect(jsonPath("$.errors[0]", equalTo("Please provide the credit card id of Payment")))
        .andReturn()
        .getResponse()
        .getContentAsString();

  }
  
  @Test
  @Rollback
  @SneakyThrows
  public void postPaymentWithCreditCardAndBankSlip() {
    
    final PaymentRequestDto paymentRequestDto = this.paymentRequestDtoFactory.simpleTypeBankSlip();
    paymentRequestDto.setCreditCard(PaymentCreditCardRequestDto.builder()
        .id(UUID.randomUUID())
        .build());
    
    this.mockMvc.perform(
        post(this.uri)
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsBytes(paymentRequestDto)))
        .andDo(print())
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status", equalTo(400)))
        .andExpect(jsonPath("$.errors[0]", equalTo("Field creditCard can't be provided when type is BANK_SLIP")))
        .andReturn()
        .getResponse()
        .getContentAsString();

  }
  
  @Test
  @Rollback
  @SneakyThrows
  public void postPaymentWithInvalidMinValueAmount() {
    final String min = PaymentRequestDto.class.getDeclaredField("amount").getAnnotation(DecimalMin.class).value();
    final PaymentRequestDto paymentRequestDto = this.paymentRequestDtoFactory.simpleTypeBankSlip();
    paymentRequestDto.setAmount(new BigDecimal(0.00).setScale(2, RoundingMode.CEILING));
    
    this.mockMvc.perform(
        post(this.uri)
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsBytes(paymentRequestDto)))
        .andDo(print())
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status", equalTo(400)))
        .andExpect(jsonPath("$.errors[0]", equalTo("must be greater than or equal to " + min)))
        .andReturn()
        .getResponse()
        .getContentAsString();

  }
  
  @Test
  @Rollback
  @SneakyThrows
  public void postPaymentWithInvalidMaxValueAmount() {
    final String max = PaymentRequestDto.class.getDeclaredField("amount").getAnnotation(DecimalMax.class).value();
    final PaymentRequestDto paymentRequestDto = this.paymentRequestDtoFactory.simpleTypeBankSlip();
    paymentRequestDto.setAmount(new BigDecimal(19999999999999.99).setScale(2, RoundingMode.CEILING));
    
    this.mockMvc.perform(
        post(this.uri)
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsBytes(paymentRequestDto)))
        .andDo(print())
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status", equalTo(400)))
        .andExpect(jsonPath("$.errors[0]", equalTo("must be less than or equal to " + max)))
        .andReturn()
        .getResponse()
        .getContentAsString();

  }
  
  @Test
  @Rollback
  @SneakyThrows
  public void postPaymentWithInvalidValueAmount() {
    Digits digits = PaymentRequestDto.class.getDeclaredField("amount").getAnnotation(Digits.class);
    final Integer fraction = digits.fraction();
    final Integer integer = digits.integer();
    final PaymentRequestDto paymentRequestDto = this.paymentRequestDtoFactory.simpleTypeBankSlip();
    paymentRequestDto.setAmount(new BigDecimal(0.01));
    
    this.mockMvc.perform(
        post(this.uri)
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsBytes(paymentRequestDto)))
        .andDo(print())
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status", equalTo(400)))
        .andExpect(jsonPath("$.errors[0]", equalTo("numeric value out of bounds (<" + integer + " digits>.<" + fraction + " digits> expected)")))
        .andReturn()
        .getResponse()
        .getContentAsString();

  }
  
  @Test
  @Rollback
  @SneakyThrows
  public void postPaymentWithBuyerNotFound() {
    final PaymentRequestDto paymentRequestDto = this.paymentRequestDtoFactory.simpleTypeBankSlip();
    paymentRequestDto.getBuyer().setId(UUID.randomUUID());
    
    this.mockMvc.perform(
        post(this.uri)
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsBytes(paymentRequestDto)))
        .andDo(print())
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status", equalTo(404)))
        .andExpect(jsonPath("$.errors[0]", equalTo("Buyer not found")))
        .andReturn()
        .getResponse()
        .getContentAsString();
  }
  
  @Test
  @Rollback
  @SneakyThrows
  public void postPaymentWithClientNotFound() {
    BuyerEntity buyerEntity = this.buyerEntityFactory.simple();
    buyerEntity = this.buyerRepository.save(buyerEntity);
    
    final PaymentRequestDto paymentRequestDto = this.paymentRequestDtoFactory.simpleTypeBankSlip();
    paymentRequestDto.setBuyer(this.modelMapper.map(
        buyerEntity, PaymentBuyerRequestDto.class));
    paymentRequestDto.getClient().setId(UUID.randomUUID());
    
    this.mockMvc.perform(
        post(this.uri)
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsBytes(paymentRequestDto)))
        .andDo(print())
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status", equalTo(404)))
        .andExpect(jsonPath("$.errors[0]", equalTo("Client not found")))
        .andReturn()
        .getResponse()
        .getContentAsString();
  }
  
  @Test
  @Rollback
  @SneakyThrows
  public void postPaymentWithCreditCardNotFound() {
    BuyerEntity buyerEntity = this.buyerEntityFactory.simple();
    buyerEntity = this.buyerRepository.save(buyerEntity);
    
    ClientEntity clientEntity = this.clientEntityFactory.simple();
    clientEntity = this.clientRepository.save(clientEntity);
    
    final PaymentRequestDto paymentRequestDto = this.paymentRequestDtoFactory.simpleTypeCreditCard();
    
    paymentRequestDto.setBuyer(this.modelMapper.map(
        buyerEntity, PaymentBuyerRequestDto.class));
    
    paymentRequestDto.setClient(this.modelMapper.map(
        clientEntity, PaymentClientRequestDto.class));
    
    paymentRequestDto.getCreditCard().setId(UUID.randomUUID());
    
    this.mockMvc.perform(
        post(this.uri)
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsBytes(paymentRequestDto)))
        .andDo(print())
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status", equalTo(404)))
        .andExpect(jsonPath("$.errors[0]", equalTo("CreditCard not found")))
        .andReturn()
        .getResponse()
        .getContentAsString();
  }
  
  @Test
  @Rollback
  @SneakyThrows
  public void getPaymentCreditCardByIdSuccess() {
    BuyerEntity buyerEntity = this.buyerEntityFactory.simple();
    buyerEntity = this.buyerRepository.save(buyerEntity);
    
    ClientEntity clientEntity = this.clientEntityFactory.simple();
    clientEntity = this.clientRepository.save(clientEntity);
    
    CreditCardEntity creditCardEntity = this.creditCardEntityFactory.simple();
    creditCardEntity.setBuyer(buyerEntity);
    creditCardEntity = this.creditCardRepository.save(creditCardEntity);
    
    PaymentEntity paymentEntity = this.paymentEntityFactory.simple();
    paymentEntity.setBuyer(buyerEntity);
    paymentEntity.setClient(clientEntity);
    paymentEntity.setCreditCard(creditCardEntity);
    paymentEntity = this.paymentRepository.save(paymentEntity);
    
    PaymentResponseDto paymentResponseDto = this.modelMapper.map(
        paymentEntity, PaymentResponseDto.class);

    final String uri = MessageFormat.format("{0}/{1}", this.uri, paymentEntity.getId());
    this.mockMvc.perform(
        get(uri)
        .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(paymentResponseDto.getId().toString()))
        .andExpect(jsonPath("$.status").value(paymentResponseDto.getStatus().getText()))
        .andExpect(jsonPath("$.barcodeNumber").doesNotExist())
        .andExpect(jsonPath("$.amount").value(paymentResponseDto.getAmount()))
        .andExpect(jsonPath("$.type").value(paymentResponseDto.getType().getText()))
        .andExpect(jsonPath("$.client.id").value(paymentResponseDto.getClient().getId().toString()))
        .andExpect(jsonPath("$.buyer.id").value(paymentResponseDto.getBuyer().getId().toString()))
        .andExpect(jsonPath("$.creditCard.id").value(paymentResponseDto.getCreditCard().getId().toString()))
        .andReturn()
        .getResponse()
        .getContentAsString();

  }
  
  @Test
  @Rollback
  @SneakyThrows
  public void getPaymentBankSlipByIdSuccess() {
    BuyerEntity buyerEntity = this.buyerEntityFactory.simple();
    buyerEntity = this.buyerRepository.save(buyerEntity);
    
    ClientEntity clientEntity = this.clientEntityFactory.simple();
    clientEntity = this.clientRepository.save(clientEntity);
    
    PaymentEntity paymentEntity = this.paymentEntityFactory.simpleTypeBankSlip();
    paymentEntity.setBuyer(buyerEntity);
    paymentEntity.setClient(clientEntity);
    
    paymentEntity = this.paymentRepository.save(paymentEntity);
    
    PaymentResponseDto paymentResponseDto = this.modelMapper.map(
        paymentEntity, PaymentResponseDto.class);

    final String uri = MessageFormat.format("{0}/{1}", this.uri, paymentEntity.getId());
    this.mockMvc.perform(
        get(uri)
        .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(paymentResponseDto.getId().toString()))
        .andExpect(jsonPath("$.status").doesNotExist())
        .andExpect(jsonPath("$.barcodeNumber").value(paymentResponseDto.getBarcodeNumber()))
        .andExpect(jsonPath("$.amount").value(paymentResponseDto.getAmount()))
        .andExpect(jsonPath("$.type").value(paymentResponseDto.getType().getText()))
        .andExpect(jsonPath("$.client.id").value(paymentResponseDto.getClient().getId().toString()))
        .andExpect(jsonPath("$.buyer.id").value(paymentResponseDto.getBuyer().getId().toString()))
        .andExpect(jsonPath("$.creditCard").doesNotExist())
        .andReturn()
        .getResponse()
        .getContentAsString();

  }
  
  @Test
  @Rollback
  @SneakyThrows
  public void getPaymentByIdPaymentNotFound() {
    final String uri = MessageFormat.format("{0}/{1}", this.uri, UUID.randomUUID());
    this.mockMvc.perform(
        get(uri)
        .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.status", equalTo(404)))
        .andExpect(jsonPath("$.errors[0]", equalTo("Payment not found")))
        .andReturn()
        .getResponse()
        .getContentAsString();

  }
  
}
