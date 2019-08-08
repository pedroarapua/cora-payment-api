package com.organization.payment.v1.dto;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotNull;

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
public class BuyerResponseDto extends BuyerRequestDto implements Serializable {

  private static final long serialVersionUID = -6870181596836157424L;

  @NotNull
  private UUID id;
  
}
