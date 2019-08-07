package com.organization.payment.entity;

import com.organization.payment.commons.EntityGenericTest;
import com.organization.payment.entity.ClientEntity;

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

