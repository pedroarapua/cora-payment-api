package com.organization.payment.business.impl;

import com.organization.payment.business.PaymentBusiness;
import com.organization.payment.entity.PaymentEntity;
import com.organization.payment.enumeration.PaymentCreditCardStatusEnum;
import com.organization.payment.enumeration.PaymentTypeEnum;
import com.organization.payment.repository.PaymentRepository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PaymentBusinessImpl implements PaymentBusiness {

  private final PaymentRepository paymentRepository;

  @Autowired
  public PaymentBusinessImpl(final PaymentRepository paymentRepository) {
    this.paymentRepository = paymentRepository;
  }

  @Override
  public PaymentEntity save(final PaymentEntity payment) {
    if (payment.getType() == PaymentTypeEnum.CREDIT_CARD) {
      payment.setStatus(PaymentCreditCardStatusEnum.getRandom());
    } else {
      payment.setBarcodeNumber("11111111111111111111111111111111111111111111");
    }
    
    return this.paymentRepository.save(payment);
  }
  
  @Override
  public Optional<PaymentEntity> findById(UUID id) {
    return this.paymentRepository.findById(id);
  }
  
}
