package com.artsgard.sociodbbatch.bank.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author wdragstra
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "AccountTransfer")
@Table(name = "account_transfer")
@IdClass(AccountTransferId.class)
public class AccountTransfer implements Serializable {

    @Id
    @Column(name="account_id", nullable = true)
    private Long accountId;

    @Id
    @Column(name="account_transfer_id", nullable = true)
    private Long accountTransferId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", updatable = false, insertable = false,
            referencedColumnName = "id")
    private Account account;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_transfer_id", updatable = false, insertable = false,
            referencedColumnName = "id")
    private Account accountTransfer;
    
    @Column(name="amount", nullable = false)
    private BigDecimal amount;
  
    @Column(name="description", nullable = true)
    private String description;
    
    @Column(name="transfer_date", nullable = false)
    private Date transferDate;
  
}
