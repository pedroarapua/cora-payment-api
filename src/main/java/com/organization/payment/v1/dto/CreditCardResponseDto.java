package com.organization.payment.v1.dto;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
public class CreditCardResponseDto extends CreditCardRequestDto implements Serializable {

  private static final long serialVersionUID = 5267931934989313385L;
  
  @NotNull
  private UUID id;

  @NotBlank
  @Size(max = 100)
  private String issuer;
  
}
