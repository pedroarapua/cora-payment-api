package com.organization.payment.factory;

import com.organization.payment.entity.ClientEntity;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class ClientEntityFactory extends AbstractFactory<ClientEntity> {

  @Override
  public ClientEntity simple() {
    return ClientEntity.builder()
      .id(UUID.randomUUID())
      .name("Empresa 1")
      .build();
  }
}

