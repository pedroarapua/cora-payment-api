package com.cora.payment.business.impl;

import com.cora.payment.business.ClientBusiness;
import com.cora.payment.entity.ClientEntity;
import com.cora.payment.repository.ClientRepository;

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

}

