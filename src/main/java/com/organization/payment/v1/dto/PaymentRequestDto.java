package com.organization.payment.v1.dto;

import com.organization.payment.annotation.NotNullIfAnotherFieldHasValue;
import com.organization.payment.annotation.NullIfAnotherFieldHasValue;
import com.organization.payment.enumeration.PaymentTypeEnum;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@NotNullIfAnotherFieldHasValue(
    fieldName = "type", 
    fieldValue = "CREDIT_CARD", 
    dependFieldName = "creditCard",
    message = "Field creditCard is required when type is CREDIT_CARD")
@NullIfAnotherFieldHasValue(
    fieldName = "type", 
    fieldValue = "BANK_SLIP", 
    dependFieldName = "creditCard",
    message = "Field creditCard can't be provided when type is BANK_SLIP")
public class PaymentRequestDto implements Serializable {

  private static final long serialVersionUID = 5267931934989313385L;

  @NotNull(message = "Please provide the amount of Payment")
  @DecimalMin(value = "0.01", inclusive = true)
  @DecimalMax(value = "9999999999999.99", inclusive = true)
  @Digits(fraction = 2, integer = 15)
  private BigDecimal amount;

  @NotNull(message = "Please provide the payment type of Payment")
  private PaymentTypeEnum type;

  @NotNull(message = "Please provide the client of Payment")
  @Valid
  private PaymentClientRequestDto client;
  
  @NotNull(message = "Please provide the buyer of Payment")
  @Valid
  private PaymentBuyerRequestDto buyer;
  
  @Valid
  private PaymentCreditCardRequestDto creditCard;
  
}
