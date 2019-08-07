package com.organization.payment.commons;

import com.github.javafaker.Faker;
import com.organization.payment.PaymentApplication;
import com.organization.payment.config.SpringConfigurationTest;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = {PaymentApplication.class, SpringConfigurationTest.class})
@TestPropertySource(properties = {"spring.config.location=classpath:application-test.yml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public abstract class ContextGenericTest {

  private final Faker faker = new Faker();

  protected Faker faker() {
    return this.faker;
  }

}
