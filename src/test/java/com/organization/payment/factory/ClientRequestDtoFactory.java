package com.organization.payment.factory;

import org.springframework.stereotype.Service;

import com.organization.payment.v1.dto.ClientRequestDto;

@Service
public class ClientRequestDtoFactory extends AbstractFactory<ClientRequestDto> {

  @Override
  public ClientRequestDto simple() {
    return ClientRequestDto.builder()
      .name("Empresa 1")
      .build();
  }
}

