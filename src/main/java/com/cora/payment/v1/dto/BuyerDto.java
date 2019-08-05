package com.cora.payment.v1.dto;

import java.io.Serializable;

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
public class BuyerDto implements Serializable {

  private static final long serialVersionUID = -6870181596836157424L;

  private String name;
  private String email;
  private String cpf;
  
}
