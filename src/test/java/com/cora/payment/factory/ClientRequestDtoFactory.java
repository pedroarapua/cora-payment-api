package com.cora.payment.factory;

import com.cora.payment.v1.dto.ClientRequestDto;

import org.springframework.stereotype.Service;

@Service
public class ClientRequestDtoFactory extends AbstractFactory {

  @Override
  public ClientRequestDto simple() {
    return ClientRequestDto.builder()
      .name("Empresa 1")
      .build();
  }
}

