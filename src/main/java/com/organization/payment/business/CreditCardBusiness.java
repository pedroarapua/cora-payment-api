package com.organization.payment.business;

import java.util.Optional;
import java.util.UUID;

import com.organization.payment.entity.CreditCardEntity;

public interface CreditCardBusiness {

  CreditCardEntity save(final CreditCardEntity creditCard);

  Optional<CreditCardEntity> findById(final UUID id);
}


