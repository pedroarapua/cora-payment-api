package com.organization.payment.business;

import java.util.Optional;
import java.util.UUID;

import com.organization.payment.entity.BuyerEntity;

public interface BuyerBusiness {

  BuyerEntity save(BuyerEntity client);
  Optional<BuyerEntity> findById(final UUID id);

}

