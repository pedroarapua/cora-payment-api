package com.organization.payment.repository;

import com.organization.payment.commons.RepositoryGenericTest;
import com.organization.payment.entity.BuyerEntity;
import com.organization.payment.entity.ClientEntity;
import com.organization.payment.entity.CreditCardEntity;
import com.organization.payment.entity.PaymentEntity;
import com.organization.payment.factory.BuyerEntityFactory;
import com.organization.payment.factory.ClientEntityFactory;
import com.organization.payment.factory.CreditCardEntityFactory;
import com.organization.payment.factory.PaymentEntityFactory;
import com.organization.payment.repository.BuyerRepository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PaymentRepositoryTest extends RepositoryGenericTest {

  @Autowired
  private PaymentRepository paymentRepository;
  
  @Autowired
  private BuyerRepository buyerRepository;
  
  @Autowired
  private ClientRepository clientRepository;
  
  @Autowired
  private CreditCardRepository creditCardRepository;

  @Autowired
  private PaymentEntityFactory paymentFactory;
  
  @Autowired
  private BuyerEntityFactory buyerEntityFactory;
  
  @Autowired
  private ClientEntityFactory clientEntityFactory;
  
  @Autowired
  private CreditCardEntityFactory creditCardEntityFactory;
  
  private BuyerEntity buyerEntity;
  private ClientEntity clientEntity;
  private CreditCardEntity creditCardEntity;
  
  @Before
  public void setUp() {
    this.buyerEntity = this.buyerEntityFactory.simple();
    this.buyerEntity = this.buyerRepository.save(this.buyerEntity);
    
    this.clientEntity = this.clientEntityFactory.simple();
    this.clientEntity = this.clientRepository.save(this.clientEntity);
    
    this.creditCardEntity = this.creditCardEntityFactory.simple();
    this.creditCardEntity.setBuyer(this.buyerEntity);
    this.creditCardEntity = this.creditCardRepository.save(this.creditCardEntity);
  }
  
  @After
  public final void tearDown() {
    this.paymentRepository.deleteAll();
    this.creditCardRepository.delete(this.creditCardEntity);
    this.clientRepository.delete(this.clientEntity);
    this.buyerRepository.delete(this.buyerEntity);
  }

  @Test
  public void save() {
    PaymentEntity paymentSeed = this.paymentFactory.simple();
    paymentSeed.setBuyer(this.buyerEntity);
    paymentSeed.setClient(this.clientEntity);
    paymentSeed.setCreditCard(this.creditCardEntity);
    PaymentEntity payment = this.paymentRepository.save(paymentSeed);

    Assert.assertNotNull(payment);
    
    PaymentEntity paymentSaved = this.paymentRepository.findById(payment.getId()).orElse(null);
    Assert.assertNotNull(paymentSaved);
    Assert.assertEquals(paymentSeed.getAmount(), paymentSaved.getAmount());
    Assert.assertEquals(paymentSeed.getStatus(), paymentSaved.getStatus());
    Assert.assertEquals(paymentSeed.getType(), paymentSaved.getType());
    Assert.assertEquals(paymentSeed.getBuyer().getId(), paymentSaved.getBuyer().getId());
    Assert.assertEquals(paymentSeed.getClient().getId(), paymentSaved.getClient().getId());
    Assert.assertEquals(paymentSeed.getCreditCard().getId(), paymentSaved.getCreditCard().getId());
    Assert.assertNotNull(paymentSaved.getId());

  }

}

