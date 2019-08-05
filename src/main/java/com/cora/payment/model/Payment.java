package com.cora.payment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
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
@EqualsAndHashCode(of = "id")
@Builder
@Entity
@Table(name = "payments")
public class Payment implements Serializable {

  @Id
  @Type(type = "uuid-char")
  private String id;

  @Column
  private String clientId;

  @Column
  private String buyerName;

  @Column
  private String buyerEmail;

  @Column
  private String buyerCpf;

  @Column
  private BigDecimal amount;
  
  @Column
  private String type;
  
  @Column
  private String cardHolderName;
  
  @Column
  private String cardNumber;
  
  @Column
  private String cardExpirationDate;
  
  @Column
  private String cardCvv;

  @PrePersist
  protected void prePersist() {
    id = UUID.randomUUID();
  }

}
