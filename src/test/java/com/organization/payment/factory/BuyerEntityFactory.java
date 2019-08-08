package com.organization.payment.factory;

import com.organization.payment.entity.BuyerEntity;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class BuyerEntityFactory extends AbstractFactory<BuyerEntity> {

  @Override
  public BuyerEntity simple() {
    return BuyerEntity.builder()
      .id(UUID.randomUUID())
      .name("Buyer 1")
      .email("teste@gmail.com")
      .cpf("10228410096")
      .build();
  }
}

