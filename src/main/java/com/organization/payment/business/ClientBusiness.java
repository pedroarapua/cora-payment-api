package com.organization.payment.business;

import java.util.Optional;
import java.util.UUID;

import com.organization.payment.entity.ClientEntity;

public interface ClientBusiness {

  ClientEntity save(ClientEntity client);
  
  Optional<ClientEntity> findById(final UUID id);

}

