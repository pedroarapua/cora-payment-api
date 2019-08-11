package com.organization.payment.entity;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
@Builder
@Entity
@Table(name = "buyers")
public class BuyerEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  
  @Id
  @Type(type = "uuid-char")
  private UUID id;
  
  @Column(nullable = false)
  private String name;
  
  @Column(nullable = false)
  private String email;
  
  @Column(nullable = false)
  private String cpf;
  
  @OneToMany(mappedBy = "buyer")
  private List<CreditCardEntity> creditCards;
  
  @OneToMany(mappedBy = "buyer")
  private List<PaymentEntity> payments;

  @PrePersist
  protected void prePersist() {
    id = UUID.randomUUID();
  }

}
