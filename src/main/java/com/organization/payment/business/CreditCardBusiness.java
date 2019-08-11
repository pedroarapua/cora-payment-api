package com.organization.payment.business;

import com.organization.payment.entity.CreditCardEntity;

import java.util.Optional;
import java.util.UUID;

public interface CreditCardBusiness {

  CreditCardEntity save(final CreditCardEntity creditCard);

  Optional<CreditCardEntity> findById(final UUID id);
}


