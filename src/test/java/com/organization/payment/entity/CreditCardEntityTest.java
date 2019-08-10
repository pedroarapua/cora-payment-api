package com.organization.payment.entity;

import com.organization.payment.commons.EntityGenericTest;
import com.organization.payment.entity.CreditCardEntity;

import org.junit.Assert;
import org.junit.Test;

public class CreditCardEntityTest extends EntityGenericTest<CreditCardEntity> {

  @Test
  public void prePersist() {
    CreditCardEntity build = CreditCardEntity.builder().build();
    build.prePersist();
    Assert.assertNotNull(build.getId());
  }
}

