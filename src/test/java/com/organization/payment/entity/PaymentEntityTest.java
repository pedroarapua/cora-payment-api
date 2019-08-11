package com.organization.payment.entity;

import com.organization.payment.commons.EntityGenericTest;
import com.organization.payment.entity.PaymentEntity;

import org.junit.Assert;
import org.junit.Test;

public class PaymentEntityTest extends EntityGenericTest<PaymentEntity> {

  @Test
  public void prePersist() {
    PaymentEntity build = PaymentEntity.builder().build();
    build.prePersist();
    Assert.assertNotNull(build.getId());
  }
}

