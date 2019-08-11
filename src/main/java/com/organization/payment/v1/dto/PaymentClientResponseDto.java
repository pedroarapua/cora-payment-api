package com.organization.payment.v1.dto;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
public class PaymentClientResponseDto extends ClientResponseDto implements Serializable {

  private static final long serialVersionUID = 5267931934989313385L;

}
