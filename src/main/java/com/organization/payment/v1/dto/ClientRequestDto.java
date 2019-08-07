package com.organization.payment.v1.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

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
public class ClientRequestDto implements Serializable {

  private static final long serialVersionUID = -6870181596836157424L;

  @NotBlank(message = "Please provide the name of Client")
  private String name;
  
}
