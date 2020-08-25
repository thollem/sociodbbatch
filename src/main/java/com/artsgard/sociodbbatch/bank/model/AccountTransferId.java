package com.artsgard.sociodbbatch.bank.model;

import java.io.Serializable;

/**
 *
 * @author artsgard
 */  
public class AccountTransferId implements Serializable {
    private AccountTransferId() { }
    private Long accountId;
    private Long accountTransferId;

    @Override
    public int hashCode() {
        return (int) (accountId + accountTransferId);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof AccountTransferId) {
            AccountTransferId otherId = (AccountTransferId) object;
            return (otherId.accountId == this.accountId)
                    && (otherId.accountTransferId == this.accountTransferId);
        }
        return false;
    }
}