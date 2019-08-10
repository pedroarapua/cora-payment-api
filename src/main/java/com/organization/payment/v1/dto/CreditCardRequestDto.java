package com.organization.payment.v1.dto;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.organization.payment.annotation.CreditCardNumber;

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
public class CreditCardRequestDto implements Serializable {

  private static final long serialVersionUID = 5267931934989313385L;

  @NotBlank(message = "Please provide the number of CreditCard")
  @Size(max = 100)
  @CreditCardNumber(message = "Please provide a valid CreditCard")
  // TODO: add validation to credit card number
  private String number;

  @NotBlank(message = "Please provide the holder name of CreditCard")
  @Size(max = 100)
  private String holderName;

  @NotNull(message = "Please provide the month of CreditCard")
  @Min(1)
  @Max(12)
  private Integer month;
  
  @NotNull(message = "Please provide the year of CreditCard")
  @Min(2019)
  private Integer year;
  
}
