package com.cora.payment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class PaymentApplicationTest {

  @Test
  public void main() {
    PaymentApplication.main(new String[] {"--spring.config.location=classpath:application-test.yml"});
  }

}
