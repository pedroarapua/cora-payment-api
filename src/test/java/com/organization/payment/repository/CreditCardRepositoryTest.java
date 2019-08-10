package com.organization.payment.repository;

import com.organization.payment.commons.RepositoryGenericTest;
import com.organization.payment.entity.BuyerEntity;
import com.organization.payment.entity.CreditCardEntity;
import com.organization.payment.factory.BuyerEntityFactory;
import com.organization.payment.factory.CreditCardEntityFactory;
import com.organization.payment.repository.BuyerRepository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CreditCardRepositoryTest extends RepositoryGenericTest {

  @Autowired
  private CreditCardRepository creditCardRepository;
  
  @Autowired
  private BuyerRepository buyerRepository;

  @Autowired
  private CreditCardEntityFactory creditCardFactory;
  
  @Autowired
  private BuyerEntityFactory buyerEntityFactory;
  
  private BuyerEntity buyerEntity;
  
  @Before
  public void setUp() {
    this.buyerEntity = this.buyerEntityFactory.simple();
    this.buyerEntity = this.buyerRepository.save(this.buyerEntity);
  }
  
  @After
  public final void tearDown() {
    this.creditCardRepository.deleteAll();
    this.buyerRepository.delete(this.buyerEntity);
  }

  @Test
  public void save() {
    CreditCardEntity creditCardSeed = this.creditCardFactory.simple();
    creditCardSeed.setBuyer(this.buyerEntity);
    CreditCardEntity creditCard = this.creditCardRepository.save(creditCardSeed);

    Assert.assertNotNull(creditCard);
    
    CreditCardEntity creditCardSaved = this.creditCardRepository.findById(creditCard.getId()).orElse(null);
    Assert.assertNotNull(creditCardSaved);
    Assert.assertEquals(creditCardSeed.getHolderName(), creditCardSaved.getHolderName());
    Assert.assertEquals(creditCardSeed.getIssuer(), creditCardSaved.getIssuer());
    Assert.assertEquals(creditCardSeed.getNumber(), creditCardSaved.getNumber());
    Assert.assertEquals(creditCardSeed.getMonth(), creditCardSaved.getMonth());
    Assert.assertEquals(creditCardSeed.getYear(), creditCardSaved.getYear());
    Assert.assertNotNull(creditCardSaved.getId());

  }

}

