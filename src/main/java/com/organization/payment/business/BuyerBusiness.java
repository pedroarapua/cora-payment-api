package com.organization.payment.business;

import com.organization.payment.entity.BuyerEntity;

import java.util.Optional;
import java.util.UUID;

public interface BuyerBusiness {

  BuyerEntity save(BuyerEntity client);
  
  Optional<BuyerEntity> findById(final UUID id);

}

