package com.organization.payment.business;

import com.organization.payment.entity.PaymentEntity;

public interface PaymentBusiness {

  PaymentEntity save(PaymentEntity payment);
  
}

