package com.cora.payment.v1.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cora.payment.v1.dto.ClientDto;

@Component
public class ClientMapper {
  
  public ClientDto serialize(final Payment payment) {
    return ClientDto.builder()
        .id(payment.getClientId());
  }

}
