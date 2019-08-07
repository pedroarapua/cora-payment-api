package com.organization.payment.commons;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.organization.payment.PaymentApplication;

@SpringBootTest(classes = {PaymentApplication.class})
@TestPropertySource(properties = {"spring.config.location=classpath:application-test.yml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public abstract class RepositoryGenericTest {

}

