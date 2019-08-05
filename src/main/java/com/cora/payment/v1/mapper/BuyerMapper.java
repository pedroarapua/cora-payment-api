package com.cora.payment.v1.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cora.payment.v1.dto.BuyerDto;

@Component
public class BuyerMapper {
  
  public BuyerDto serialize(final Buyer buyer) {
    return BuyerDto.builder()
        .cpf(buyer.getCpf())
        .name(buyer.getName())
        .email(buyer.getEmail());
  }

}
