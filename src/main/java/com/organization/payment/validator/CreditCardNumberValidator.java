package com.organization.payment.validator;

import br.com.moip.validators.CreditCard;

import com.organization.payment.annotation.CreditCardNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CreditCardNumberValidator implements ConstraintValidator<CreditCardNumber, String> {

  @Override
  public void initialize(CreditCardNumber constraintAnnotation) {
    
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return value == null || value.isEmpty() || new CreditCard(value).isValid();
  }
}
