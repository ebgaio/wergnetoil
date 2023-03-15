package com.wergnet.wergnetoil.api.transaction.model;

import com.wergnet.wergnetoil.api.bank.model.Bank;
import com.wergnet.wergnetoil.api.card.model.Card;
import com.wergnet.wergnetoil.api.customer.model.Customer;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_transaction")
  private Long id;

  private String description;
  private BigDecimal valueTransaction;

  @Column(name = "date_Credit")
  private LocalDate dateCredit;

  @Column(name = "date_Debit")
  private LocalDate dateDebit;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "customer_id", nullable = false)
  private Customer customer;

  @ManyToOne
  @JoinColumn(name = "bank_id", nullable = false)
  private Bank bank;

  @ManyToOne
  @JoinColumn(name = "card_id", nullable = false)
  private Card card;

}
