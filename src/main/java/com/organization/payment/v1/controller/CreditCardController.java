package com.organization.payment.v1.controller;

import com.organization.payment.business.BuyerBusiness;
import com.organization.payment.business.CreditCardBusiness;
import com.organization.payment.entity.BuyerEntity;
import com.organization.payment.entity.CreditCardEntity;
import com.organization.payment.v1.dto.CreditCardRequestDto;
import com.organization.payment.v1.dto.CreditCardResponseDto;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/paymentapi/buyers")
@Api(value = "Buyer")
public class CreditCardController {

  private final CreditCardBusiness creditCardBusiness;
  private final BuyerBusiness buyerBusiness;
  private final ModelMapper modelMapper;
  
  @Autowired public CreditCardController(
      final CreditCardBusiness creditCardBusiness,
      final BuyerBusiness buyerBusiness,
      final ModelMapper modelMapper) { 
    this.creditCardBusiness = creditCardBusiness;
    this.buyerBusiness = buyerBusiness;
    this.modelMapper = modelMapper;
  }

  @ApiOperation(value = "Create a new Credit Card to the Client")
  @PostMapping(value = "/{id}/creditcards", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(code = HttpStatus.CREATED)
  @ResponseBody
  public CreditCardResponseDto post(
      @PathVariable final UUID id,
      @RequestBody @Valid final CreditCardRequestDto creditCardRequestDto) {
    
    final Optional<BuyerEntity> opBuyer = buyerBusiness.findById(id);

    final BuyerEntity buyer = opBuyer.orElseThrow(() ->
        new EntityNotFoundException("Buyer"));

    
    CreditCardEntity creditCardEntity = this.modelMapper.map(creditCardRequestDto, 
        CreditCardEntity.class);
    creditCardEntity.setBuyer(buyer);
    
    return this.modelMapper.map(
        this.creditCardBusiness.save(creditCardEntity), CreditCardResponseDto.class);

  }

}
