package com.organization.payment.entity;

import com.organization.payment.commons.EntityGenericTest;
import com.organization.payment.entity.BuyerEntity;

import org.junit.Assert;
import org.junit.Test;

public class BuyerEntityTest extends EntityGenericTest<BuyerEntity> {

  @Test
  public void prePersist() {
    BuyerEntity build = BuyerEntity.builder().build();
    build.prePersist();
    Assert.assertNotNull(build.getId());
  }
}

