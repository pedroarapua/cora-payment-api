package com.organization.payment.business;

import com.organization.payment.entity.ClientEntity;

import java.util.Optional;
import java.util.UUID;

public interface ClientBusiness {

  ClientEntity save(ClientEntity client);
  
  Optional<ClientEntity> findById(final UUID id);

}

