package com.organization.payment.business.impl;

import com.organization.payment.business.CreditCardBusiness;
import com.organization.payment.entity.CreditCardEntity;
import com.organization.payment.repository.CreditCardRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreditCardBusinessImpl implements CreditCardBusiness {

  private final CreditCardRepository creditCardRepository;

  @Autowired
  public CreditCardBusinessImpl(final CreditCardRepository creditCardRepository) {
    this.creditCardRepository = creditCardRepository;
  }

  @Override
  public CreditCardEntity save(final CreditCardEntity creditCard) {
    return this.creditCardRepository.save(creditCard);
  }
  
}
