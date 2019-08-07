package com.organization.payment.repository;

import com.organization.payment.commons.RepositoryGenericTest;
import com.organization.payment.entity.ClientEntity;
import com.organization.payment.factory.ClientEntityFactory;
import com.organization.payment.repository.ClientRepository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ClientRepositoryTest extends RepositoryGenericTest {

  @Autowired
  private ClientRepository clientRepository;

  @Autowired
  private ClientEntityFactory clientFactory;

  @Test
  public void save() {
    ClientEntity client = this.clientFactory.simple();
    ClientEntity clientSaved = this.clientRepository.save(client);

    Assert.assertNotNull(clientSaved.getId());
    Assert.assertEquals(client.getName(), clientSaved.getName());

    this.clientRepository.delete(clientSaved);
  }

}

