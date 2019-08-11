package com.organization.payment.factory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.organization.payment.enumeration.PaymentTypeEnum;
import com.organization.payment.v1.dto.PaymentBuyerRequestDto;
import com.organization.payment.v1.dto.PaymentClientRequestDto;
import com.organization.payment.v1.dto.PaymentCreditCardRequestDto;
import com.organization.payment.v1.dto.PaymentRequestDto;

@Service
public class PaymentRequestDtoFactory extends AbstractFactory<PaymentRequestDto> {

  @Override
  public PaymentRequestDto simple() {
    return PaymentRequestDto.builder()
      .amount(new BigDecimal(0.01).setScale(2, RoundingMode.CEILING))
      .type(PaymentTypeEnum.CREDIT_CARD)
      .buyer(PaymentBuyerRequestDto.builder()
          .id(UUID.randomUUID())
          .build())
      .client(PaymentClientRequestDto.builder()
          .id(UUID.randomUUID())
          .build())
      .creditCard(PaymentCreditCardRequestDto.builder()
          .id(UUID.randomUUID())
          .build())
      .build();
  }
  
  public PaymentRequestDto simpleTypeCreditCard() {
    return this.simple();
  }
  
  public PaymentRequestDto simpleTypeBankSlip() {
    return PaymentRequestDto.builder()
      .amount(new BigDecimal(0.01).setScale(2, RoundingMode.CEILING))
      .type(PaymentTypeEnum.BANK_SLIP)
      .buyer(PaymentBuyerRequestDto.builder()
          .id(UUID.randomUUID())
          .build())
      .client(PaymentClientRequestDto.builder()
          .id(UUID.randomUUID())
          .build())
      .build();
  }
}
