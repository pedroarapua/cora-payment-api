package com.organization.payment.factory;

import com.organization.payment.entity.ClientEntity;
import com.organization.payment.repository.ClientRepository;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientEntityFactory extends AbstractFactory {

  @Autowired
  private ClientRepository repository;

  @Override
  public ClientEntity simple() {
    return ClientEntity.builder()
      .id(UUID.randomUUID())
      .name("Empresa 1")
      .build();
  }
}

