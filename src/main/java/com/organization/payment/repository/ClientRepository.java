package com.organization.payment.repository;

import com.organization.payment.entity.ClientEntity;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, UUID> {

}

