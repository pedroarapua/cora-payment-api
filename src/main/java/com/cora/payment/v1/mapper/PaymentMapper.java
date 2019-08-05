package com.cora.payment.v1.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
  private BuyerMapper buyerMapper;
  private CardMapper cardMapper;
  private ClientMapper clientMapper;

  @Autowired
  public PaymentMapper(
      BuyerMapper buyerMapper, 
      CardMapper cardMapper, 
      ClientMapper clientMapper) {
    this.buyerMapper = buyerMapper;
    this.cardMapper = cardMapper;
    this.clientMapper = clientMapper;
  }

  public Payment deserialize(final PaymentDto paymentDto) {
    return Payment.builder()
        .number(creditCardDto.getNumber())
        .uuid(creditCardDto.getUuid())
        .holderName(creditCardDto.getHolderName())
        .month(expirationMapper.deserialize(creditCardDto.getExpiration()).getMonth())
        .year(expirationMapper.deserialize(creditCardDto.getExpiration()).getYear())
        .issuer(issuerMapper.deserialize(creditCardDto.getIssuer()).getIssuer())
        .defaultCreditCard(creditCardDto.getDefaultCreditCard())
        .updatedAt(creditCardDto.getUpdatedAt())
        .createdAt(creditCardDto.getCreatedAt())
        .build();

  }

}
