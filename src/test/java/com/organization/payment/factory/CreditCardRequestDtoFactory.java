package com.organization.payment.factory;

import org.springframework.stereotype.Service;

import com.organization.payment.v1.dto.CreditCardRequestDto;

@Service
public class CreditCardRequestDtoFactory extends AbstractFactory<CreditCardRequestDto> {

  @Override
  public CreditCardRequestDto simple() {
    return CreditCardRequestDto.builder()
      .holderName("Pedro H F Dias")
      .month(1)
      .year(2020)
      .number("5555666677778884")
      .build();
  }
}
