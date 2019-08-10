package com.organization.payment.repository;

import com.organization.payment.entity.CreditCardEntity;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCardEntity, UUID> {

}

