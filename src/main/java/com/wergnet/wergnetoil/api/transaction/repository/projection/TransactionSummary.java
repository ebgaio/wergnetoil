package com.wergnet.wergnetoil.api.transaction.repository.projection;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionSummary {

  public Long id;
  private String description;
  private BigDecimal valueTransaction;
  private LocalDate dateCredit;
  private LocalDate dateDebit;
  public String customer;
  public String bank;
  public String card;

}
