package com.cora.payment.v1.mapper;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cora.payment.v1.dto.CardDto;

@Component
public class CardMapper {
  
  public CardDto serialize(final Card card) {
    return Optional.ofNullable(card).map(c -> CardDto.builder()
        .number(c.getNumber())
        .holderName(c.getHolderName())
        .expiration(c.getExpiration())
        .build()).orElse(null);
  }

}
