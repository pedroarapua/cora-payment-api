package com.organization.payment.v1.dto;

import com.organization.payment.annotation.CreditCardNumber;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.hibernate.validator.constraints.Length;

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
  @CreditCardNumber(message = "Please provide a valid number of CreditCard")
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
  
  @NotBlank(message = "Please provide the cvv of CreditCard")
  @Length(min = 3, max = 3)
  @Pattern(regexp = "^(0|[1-9][0-9]*)$", message = "Please provide a valid cvv of CreditCard")
  private String cvv;
  
}
