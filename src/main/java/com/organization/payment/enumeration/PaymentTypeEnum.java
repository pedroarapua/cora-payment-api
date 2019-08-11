package com.organization.payment.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PaymentTypeEnum {
  BANK_SLIP("BANK_SLIP"),
  CREDIT_CARD("CREDIT_CARD");

  @Getter
  private final String text;

  @Override 
  public String toString() { 
    return text; 
  }

  public static PaymentTypeEnum fromText(String text) {
    for (PaymentTypeEnum paymentType : PaymentTypeEnum.values()) {
      if (paymentType.getText().equals(text)) {
        return paymentType;
      }
    }
    throw new IllegalArgumentException();
  }

}
