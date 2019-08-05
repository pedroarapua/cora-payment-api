package com.cora.payment.v1.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.cora.payment.v1.enumeration.PaymentTypeEnum;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
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
public class PaymentDto implements Serializable {

  private static final long serialVersionUID = -6870181596836157424L;
  
  @NotNull(message = "Please provide a client")
  private ClientDto client;
  @NotNull(message = "Please provide a buyer")
  private BuyerDto buyer;
  @NotNull(message = "Please provide an amount value")
  @DecimalMin("0.01")
  private BigDecimal amount;
  @NotEmpty(message = "Please provide a type of payment")
  private PaymentTypeEnum type;
  private CardDto card;

}

