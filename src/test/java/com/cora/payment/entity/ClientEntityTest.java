package com.cora.payment.entity;

import com.cora.payment.commons.EntityGenericTest;

import org.junit.Assert;
import org.junit.Test;

public class ClientEntityTest extends EntityGenericTest<ClientEntity> {

  @Test
  public void prePersist() {
    ClientEntity build = ClientEntity.builder().build();
    build.prePersist();
    Assert.assertNotNull(build.getId());
  }
}

