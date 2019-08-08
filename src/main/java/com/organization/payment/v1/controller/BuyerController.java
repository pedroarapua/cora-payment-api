package com.organization.payment.v1.controller;

import com.organization.payment.business.BuyerBusiness;
import com.organization.payment.entity.BuyerEntity;
import com.organization.payment.v1.dto.BuyerRequestDto;
import com.organization.payment.v1.dto.BuyerResponseDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/paymentapi/buyers")
@Api(value = "Buyer")
public class BuyerController {

  private final BuyerBusiness buyerBusiness; 
  private final ModelMapper modelMapper;
  
  @Autowired public BuyerController(
      final BuyerBusiness buyerBusiness,
      final ModelMapper modelMapper) { 
    this.buyerBusiness = buyerBusiness;
    this.modelMapper = modelMapper;
  }

  @ApiOperation(value = "Create a new Buyer")
  @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(code = HttpStatus.CREATED)
  @ResponseBody
  public BuyerResponseDto post(@RequestBody @Valid final BuyerRequestDto buyerRequestDto) {
    
    BuyerEntity buyerEntity = this.modelMapper.map(buyerRequestDto, BuyerEntity.class);
    BuyerResponseDto buyerDtoResponse = this.modelMapper.map(
        this.buyerBusiness.save(buyerEntity), BuyerResponseDto.class);

    return buyerDtoResponse;
  }

}
