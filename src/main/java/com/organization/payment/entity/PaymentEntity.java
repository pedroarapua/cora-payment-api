package com.organization.payment.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.hibernate.annotations.Type;

import com.organization.payment.enumeration.PaymentCreditCardStatusEnum;
import com.organization.payment.enumeration.PaymentTypeEnum;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
@Builder
@Entity
@Table(name = "payments")
public class PaymentEntity implements Serializable {

  private static final long serialVersionUID = -956876697219527005L;

  @Id
  @Type(type = "uuid-char")
  private UUID id;

  @Column(nullable = false)
  private BigDecimal amount;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private PaymentTypeEnum type;
  
  @Enumerated(EnumType.STRING)
  @Column(nullable = true)
  private PaymentCreditCardStatusEnum status;
  
  @Column(nullable = true)
  private String barcodeNumber;
  
  @ManyToOne(optional = false)
  @JoinColumn(name = "client_id")
  private ClientEntity client;

  @ManyToOne(optional = false)
  @JoinColumn(name = "buyer_id")
  private BuyerEntity buyer;
  
  @ManyToOne(optional = true)
  @JoinColumn(name = "credit_card_id")
  private CreditCardEntity creditCard;

  @PrePersist
  protected void prePersist() {
    this.id = UUID.randomUUID();
  }

}
