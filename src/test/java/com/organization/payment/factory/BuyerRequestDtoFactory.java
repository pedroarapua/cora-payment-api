package com.organization.payment.factory;

import org.springframework.stereotype.Service;

import com.organization.payment.v1.dto.BuyerRequestDto;

@Service
public class BuyerRequestDtoFactory extends AbstractFactory<BuyerRequestDto> {

  @Override
  public BuyerRequestDto simple() {
    return BuyerRequestDto.builder()
      .name("Buyer 1")
      .email("teste@gmail.com")
      .cpf("10228410096")
      .build();
  }
}

