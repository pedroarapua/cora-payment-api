package com.organization.payment.factory;

import com.organization.payment.entity.PaymentEntity;
import com.organization.payment.enumeration.PaymentCreditCardStatusEnum;
import com.organization.payment.enumeration.PaymentTypeEnum;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class PaymentEntityFactory extends AbstractFactory<PaymentEntity> {

  private BuyerEntityFactory buyerEntityFactory;
  private ClientEntityFactory clientEntityFactory;
  private CreditCardEntityFactory creditCardEntityFactory;
  
  public PaymentEntityFactory(
      final BuyerEntityFactory buyerEntityFactory,
      final ClientEntityFactory clientEntityFactory,
      final CreditCardEntityFactory creditCardEntityFactory) {
    this.buyerEntityFactory = buyerEntityFactory;
    this.clientEntityFactory = clientEntityFactory;
    this.creditCardEntityFactory = creditCardEntityFactory;
  }
  
  public PaymentEntity simple() {
    //TODO: add faker class to generate information
    return PaymentEntity.builder()
      .id(UUID.randomUUID())
      .amount(new BigDecimal(0.01))
      .buyer(this.buyerEntityFactory.simple())
      .client(this.clientEntityFactory.simple())
      .creditCard(this.creditCardEntityFactory.simple())
      .type(PaymentTypeEnum.CREDIT_CARD)
      .status(PaymentCreditCardStatusEnum.SUCCESS)
      .build();
  }
  
  public PaymentEntity simpleTypeBankSlip() {
    //TODO: add faker class to generate information
    return PaymentEntity.builder()
      .id(UUID.randomUUID())
      .amount(new BigDecimal(0.01))
      .buyer(this.buyerEntityFactory.simple())
      .client(this.clientEntityFactory.simple())
      .barcodeNumber("11111111111111111111111111111111111111111111")
      .type(PaymentTypeEnum.BANK_SLIP)
      .build();
  }
}

