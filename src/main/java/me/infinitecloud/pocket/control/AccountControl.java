/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package me.infinitecloud.pocket.control;

import java.util.List;
import me.infinitecloud.pocket.entity.NormalAccount;
import me.infinitecloud.pocket.exception.AccountException;
import me.infinitecloud.pocket.exception.CurrencyException;
import me.infinitecloud.pocket.exception.GroupException;
/**
 * Provides Manipulation methods for all functions related to Normal account
 * @author Aymen Alquaiti
 * Date: 31/01/2016
 */
public interface AccountControl
{
    /**
     * Creates a new normal Account
     * @param name Account Name
     * @param openBalance Opening Balance
     * @param currency Currency id
     * @param group Group id. if no group exists, provide zero or negative number
     * @return Account id
     */
    public long create(String name, long openBalance, long currency, long group);    
    
    /**
     * Retrieve an account by id. Associated objects are partially initialized
     * @param id Account id
     * @return Normal Account Found
     * @throws AccountException with state {@link AccountException#NOT_EXISTS} if
     * account was not found with specified id
     */
    public NormalAccount get(long id) throws AccountException;
    
    /**
     * Retrieve all accounts. Associated objects for each account are partially 
     * initialized
     * @return List of NormalAccount objects
     */
    public List<NormalAccount> getAll();
    
    /**
     * Update Account
     * @param id Account id
     * @param name Account Name to update to
     * @param openBalance Opening Balance to update to
     * @param currency Currency id to update to
     * @param group Account Group id to update to. Use zero or -1 to link to no
     * account
     * @throws AccountException with state {@link AccountException#NOT_EXISTS} if
     * account was not found with specified id
     * @throws CurrencyException with state {@link CurrencyException#NOT_EXISTS} if
     * currency was not found with specified id
     * @throws GroupException with state {@link GroupException#NOT_EXISTS} if
     * account group was not found with specified id
     */
    public void update(long id, String name, long openBalance, long currency,
            long group) throws AccountException, CurrencyException, GroupException;
    
    /**
     * Close account
     * @param id Account id
     * @throws AccountException with state {@link AccountException#NOT_EXISTS} if
     * account was not found with specified id
     */
    public void close(long id) throws AccountException;
    
    /**
     * Retrieve balance of specified account. To retrieve all balances of accounts,
     * used {@link #getAll()}
     * @param id Account id
     * @return Account Balance
     * @throws AccountException with state {@link AccountException#NOT_EXISTS} if
     * account was not found with specified id
     */
    public long getBalance(long id) throws AccountException;
}
