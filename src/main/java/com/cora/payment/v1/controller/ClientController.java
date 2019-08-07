package com.cora.payment.v1.controller;

import com.cora.payment.business.ClientBusiness;
import com.cora.payment.entity.ClientEntity;
import com.cora.payment.v1.dto.ClientRequestDto;
import com.cora.payment.v1.dto.ClientResponseDto;
import com.cora.payment.v1.mapper.ClientMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;

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
@RequestMapping("/v1/paymentapi/clients")
@Api(value = "Client")
public class ClientController {

  private final ClientBusiness clientBusiness; 
  private final ClientMapper clientMapper;
  
  @Autowired public ClientController(
      final ClientBusiness clientBusiness,
      final ClientMapper clientMapper) { 
    this.clientBusiness = clientBusiness;
    this.clientMapper = clientMapper; 
  }

  @ApiOperation(value = "Create a new Client")
  @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(code = HttpStatus.CREATED)
  @ResponseBody
  public ClientResponseDto post(@RequestBody @Valid final ClientRequestDto clientRequestDto) {
    
    ClientEntity clientEntity = this.clientMapper.convertToEntity(clientRequestDto);
    ClientResponseDto clientDtoResponse = this.clientMapper.convertToResponseDto(
        this.clientBusiness.save(clientEntity));
    return clientDtoResponse;
  }

}
