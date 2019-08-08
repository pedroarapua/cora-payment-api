package com.organization.payment.business.impl;

import com.organization.payment.business.BuyerBusiness;
import com.organization.payment.entity.BuyerEntity;
import com.organization.payment.repository.BuyerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BuyerBusinessImpl implements BuyerBusiness {

  private final BuyerRepository buyerRepository;

  @Autowired
  public BuyerBusinessImpl(final BuyerRepository buyerRepository) {
    this.buyerRepository = buyerRepository;
  }

  @Override
  public BuyerEntity save(BuyerEntity buyer) {
    return this.buyerRepository.save(buyer);
  }

}

