package com.artsgard.sociodbbatch.bank.repository;

import com.artsgard.sociodbbatch.bank.model.AccountTransfer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountTransferRepository extends JpaRepository<AccountTransfer, Long> {
    static final String ACCOUNT_TRANSFER_BY_IDS = 
             "SELECT * FROM account_transfer WHERE account_id=:accountId and account_transfer_id=:accountTransferId";
    
    static final String ACCOUNT_TRANSFER_BY_IBAN = 
             "SELECT account_transfer.account_id, account_transfer.account_transfer_id, "
            + "account_transfer.description FROM account_transfer JOIN account "
            + "ON account_transfer.account_id = account.id where  account.iban =:iban";
    
    static final String ACCOUNT_TRANSFER_BY_USERNAME = 
             "SELECT account_transfer.account_id, account_transfer.account_transfer_id, "
            + "account_transfer.description FROM account_transfer JOIN account "
            + "ON account_transfer.account_id = account.id where  account.username =:username";
    
    
    
    @Query(value = ACCOUNT_TRANSFER_BY_IDS, nativeQuery = true)
    public AccountTransfer getByAccountIdAndAccountTransferId(@Param("accountId") Long accountId, @Param("accountTransferId") Long accountTransferId);
    
    List<AccountTransfer> findByAccountId(Long accountId);
    
    @Query(value = ACCOUNT_TRANSFER_BY_IBAN, nativeQuery = true)
    List<AccountTransfer> findByIban(@Param("iban") String iban);
    
    @Query(value = ACCOUNT_TRANSFER_BY_USERNAME, nativeQuery = true)
    List<AccountTransfer> findByUsername(@Param("username") String username);
}
