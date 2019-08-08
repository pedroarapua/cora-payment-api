package com.organization.payment.repository;

import com.organization.payment.commons.RepositoryGenericTest;
import com.organization.payment.entity.BuyerEntity;
import com.organization.payment.factory.BuyerEntityFactory;
import com.organization.payment.repository.BuyerRepository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class BuyerRepositoryTest extends RepositoryGenericTest {

  @Autowired
  private BuyerRepository buyerRepository;

  @Autowired
  private BuyerEntityFactory buyerFactory;

  @Test
  public void save() {
    BuyerEntity buyer = this.buyerFactory.simple();
    BuyerEntity buyerSaved = this.buyerRepository.save(buyer);

    Assert.assertNotNull(buyerSaved.getId());
    Assert.assertEquals(buyer.getName(), buyerSaved.getName());
    Assert.assertEquals(buyer.getEmail(), buyerSaved.getEmail());
    Assert.assertEquals(buyer.getCpf(), buyerSaved.getCpf());

    this.buyerRepository.delete(buyerSaved);
  }

}

