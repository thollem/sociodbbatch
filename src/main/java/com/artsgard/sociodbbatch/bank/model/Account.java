package com.artsgard.sociodbbatch.bank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "account")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="username", nullable = false, unique = true)
    private String username;
    
    @Column(name="iban", nullable = false, unique = true)
    private String iban;
    
    @Column(name="balance", nullable = false)
    private BigDecimal balance;
    
    @Column(name="currency", nullable = false)
    private String currency;
    
    @Column(name="creation_date", nullable = false)
    private Date creationDate;
 
    @Column(name="active", nullable = false)
    private boolean active;
    
    @JsonIgnore
    @OneToMany(targetEntity=AccountTransfer.class, mappedBy="account")
    private List<AccountTransfer> transfers;
    
    @JsonIgnore
    @OneToMany(targetEntity=AccountTransfer.class, mappedBy="account")
    private List<AccountTransfer> accountTransfers;
}
