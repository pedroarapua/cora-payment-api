package com.organization.payment.business.impl;

import com.organization.payment.business.ClientBusiness;
import com.organization.payment.entity.ClientEntity;
import com.organization.payment.repository.ClientRepository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClientBusinessImpl implements ClientBusiness {

  private final ClientRepository clientRepository;

  @Autowired
  public ClientBusinessImpl(final ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  @Override
  public ClientEntity save(ClientEntity client) {
    return this.clientRepository.save(client);
  }
  
  @Override
  public Optional<ClientEntity> findById(UUID id) {
    return this.clientRepository.findById(id);
  }

}

