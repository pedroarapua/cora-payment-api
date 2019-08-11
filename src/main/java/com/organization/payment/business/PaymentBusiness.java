package com.organization.payment.business;

import com.organization.payment.entity.PaymentEntity;

import java.util.Optional;
import java.util.UUID;

public interface PaymentBusiness {

  PaymentEntity save(PaymentEntity payment);
  
  Optional<PaymentEntity> findById(final UUID id);
}

