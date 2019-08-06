package com.cora.payment.repository;

import com.cora.payment.commons.RepositoryGenericTest;
import com.cora.payment.factory.ClientFactory;
import com.cora.payment.entity.ClientEntity;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ClientRepositoryTest extends RepositoryGenericTest {

  @Autowired
  private ClientRepository clientRepository;

  @Autowired
  private ClientFactory clientFactory;

  @Test
  public void save() {
    ClientEntity client = this.clientFactory.simple();
    ClientEntity clientSaved = this.clientRepository.save(client);

    Assert.assertNotNull(clientSaved.getId());
    Assert.assertEquals(client.getName(), clientSaved.getName());

    this.clientRepository.delete(clientSaved);
  }

}

