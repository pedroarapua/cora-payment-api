package com.organization.payment.v1.controller;

import com.organization.payment.business.BuyerBusiness;
import com.organization.payment.business.ClientBusiness;
import com.organization.payment.business.CreditCardBusiness;
import com.organization.payment.business.PaymentBusiness;
import com.organization.payment.entity.BuyerEntity;
import com.organization.payment.entity.ClientEntity;
import com.organization.payment.entity.CreditCardEntity;
import com.organization.payment.entity.PaymentEntity;
import com.organization.payment.v1.dto.PaymentRequestDto;
import com.organization.payment.v1.dto.PaymentResponseDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/paymentapi/payments")
@Api(value = "Payment")
public class PaymentController {

  private final PaymentBusiness paymentBusiness;
  private final BuyerBusiness buyerBusiness;
  private final ClientBusiness clientBusiness;
  private final CreditCardBusiness creditCardBusiness;
  private final ModelMapper modelMapper;
  
  @Autowired public PaymentController(
      final PaymentBusiness paymentBusiness,
      final BuyerBusiness buyerBusiness,
      final ClientBusiness clientBusiness,
      final CreditCardBusiness creditCardBusiness,
      final ModelMapper modelMapper) { 
    this.paymentBusiness = paymentBusiness;
    this.buyerBusiness = buyerBusiness;
    this.clientBusiness = clientBusiness;
    this.creditCardBusiness = creditCardBusiness;
    this.modelMapper = modelMapper;
  }

  @ApiOperation(value = "Create a new Payment")
  @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(code = HttpStatus.CREATED)
  @ResponseBody
  public PaymentResponseDto post(@RequestBody @Valid final PaymentRequestDto paymentRequestDto) {
    
    final Optional<BuyerEntity> opBuyer = this.buyerBusiness
        .findById(paymentRequestDto.getBuyer().getId());
    final BuyerEntity buyer = opBuyer.orElseThrow(() ->
        new EntityNotFoundException("Buyer"));
    
    final Optional<ClientEntity> opClient = this.clientBusiness
        .findById(paymentRequestDto.getClient().getId());
    final ClientEntity client = opClient.orElseThrow(() ->
        new EntityNotFoundException("Client"));
    
    Optional<CreditCardEntity> opCreditCard = Optional.empty();
    
    if (paymentRequestDto.getCreditCard() != null) {
      opCreditCard = this.creditCardBusiness
          .findById(paymentRequestDto.getCreditCard().getId());
      
      opCreditCard.orElseThrow(() ->
          new EntityNotFoundException("CreditCard"));
    }
    
    PaymentEntity paymentEntity = this.modelMapper.map(paymentRequestDto, 
        PaymentEntity.class);
    paymentEntity.setBuyer(buyer);
    paymentEntity.setClient(client);
    
    if (opCreditCard.isPresent()) {
      paymentEntity.setCreditCard(opCreditCard.get());
    }
    
    return this.modelMapper.map(
        this.paymentBusiness.save(paymentEntity), PaymentResponseDto.class);
  }
  
  @GetMapping(value = "/{id}")
  @ApiOperation(value = "Get payment by id")
  @ResponseStatus(code = HttpStatus.OK)
  @ResponseBody
  public PaymentResponseDto findById(@PathVariable final UUID id) {
    final Optional<PaymentEntity> opPayment = this.paymentBusiness.findById(id);

    PaymentEntity paymentEntity = opPayment.orElseThrow(() ->
        new EntityNotFoundException("Payment"));
    
    return this.modelMapper.map(
        paymentEntity, PaymentResponseDto.class);
  }

}
