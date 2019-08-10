package com.organization.payment.v1.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.organization.payment.annotation.CPF;

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
public class BuyerRequestDto implements Serializable {

  private static final long serialVersionUID = -6870181596836157424L;

  @NotBlank(message = "Please provide the name of Buyer")
  @Size(max = 255)
  private String name;
  
  @NotBlank(message = "Please provide the email of Buyer")
  @Email(message = "Please provide a valid email of Buyer")
  private String email;
  
  @NotBlank(message = "Please provide the cpf of Buyer")
  @Size(min = 11, max = 11, message = "cpf entered [${validatedValue}] "
      + "is invalid. It must be between {min} and {max}")
  @CPF(message = "Please provide a valid cpf to the Buyer")
  private String cpf;
  
}
