package com.organization.payment.factory;

import com.organization.payment.entity.CreditCardEntity;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class CreditCardEntityFactory extends AbstractFactory<CreditCardEntity> {

  private BuyerEntityFactory buyerEntityFactory;
  
  public CreditCardEntityFactory(final BuyerEntityFactory buyerEntityFactory) {
    this.buyerEntityFactory = buyerEntityFactory;
  }
  
  @Override
  public CreditCardEntity simple() {
    //TODO: add faker class to generate information
    return CreditCardEntity.builder()
      .id(UUID.randomUUID())
      .holderName("Pedro H F Dias")
      .issuer("MASTERCARD")
      .month(1)
      .year(2020)
      .number("5555666677778884")
      .buyer(this.buyerEntityFactory.simple())
      .build();
  }
}

