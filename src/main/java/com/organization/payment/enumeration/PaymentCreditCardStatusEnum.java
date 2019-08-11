package com.organization.payment.enumeration;

import java.util.Random;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PaymentCreditCardStatusEnum {
  SUCCESS("success"),
  FAILED("failed");

  @Getter
  private final String text;
  
  public static PaymentCreditCardStatusEnum getRandom() {
    Random random = new Random();
    return values()[random.nextInt(values().length)];
  }
}
