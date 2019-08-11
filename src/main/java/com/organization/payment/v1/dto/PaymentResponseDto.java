package com.organization.payment.v1.dto;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.organization.payment.enumeration.PaymentCreditCardStatusEnum;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class PaymentResponseDto extends PaymentRequestDto implements Serializable {

  private static final long serialVersionUID = 5267931934989313385L;

  @NotNull
  private UUID id;
  
  private String barcodeNumber;
  
  private PaymentCreditCardStatusEnum status;
  
}
