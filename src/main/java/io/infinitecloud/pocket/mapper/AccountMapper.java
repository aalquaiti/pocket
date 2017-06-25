/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.mapper;

import io.infinitecloud.pocket.entity.AccountType;

/**
 * Mapper for table account
 * @author Aymen Alquaiti
 * <p>Date: 03/02/2016</p>
 */
public interface AccountMapper
{
    /**
     * Determine whether if account exists with specified id
     * @param id Account id
     * @return true if account exists with specified id
     */
    public boolean exists(long id);    
    
    /**
     * Insert new record
     * @param name Account name
     * @param type Account type
     * @param balance Account balance
     * @return Account id generated
     */
    public long insert(String name, AccountType type, long balance);
    
    /**
     * Update account
     * @param id Account id
     * @param name Account name to update     
     */
    public void update(long id, String name);    
    
    /**
     * Retrieve account balance
     * @param id Account id
     * @return Account Balance
     */
    public long getBalance(long id);
    
    /**
     * Update Balance by adding difference to previous Balance. E.g: If previous
     * balance was 200, and added difference is 300, the new balance will be 500
     * @param id Account id
     * @param diff Difference to add to previous balance 
     */
    public void updateBalance(long id, long diff);
}
