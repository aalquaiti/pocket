/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.control;

import java.util.List;
import io.infinitecloud.pocket.entity.AccountType;
import io.infinitecloud.pocket.entity.PeopleAccount;
import io.infinitecloud.pocket.exception.AccountException;
import io.infinitecloud.pocket.exception.CurrencyException;
import io.infinitecloud.pocket.exception.PeopleGroupException;
import io.infinitecloud.pocket.mapper.AccountMapper;
import io.infinitecloud.pocket.mapper.BalanceMapper;
import io.infinitecloud.pocket.mapper.CurrencyMapper;
import io.infinitecloud.pocket.mapper.PeopleAccountMapper;
import io.infinitecloud.pocket.mapper.PeopleGroupMapper;
import io.infinitecloud.pocket.util.ConnectionManager;

/**
 * Provides Manipulation methods for all functions related to People
 * @author Aymen Alquaiti
 * <p>Date: 10/08/2016</p>
 */
public class PeopleControl
{
    private final ConnectionManager cm;
    private final AccountMapper account;
    private final PeopleAccountMapper people;
    private final CurrencyMapper currency;
    private final PeopleGroupMapper group;
    private final BalanceMapper bal;    
    
    /**
     * Initializes Control     
     * @param cm Connection Manager
     * @param account AccountMapper
     * @param people PeopleAccountMapper
     * @param currency CurrencyMapper
     * @param group GroupMapper
     * @param bal BalanceMapper
     */
    public PeopleControl(ConnectionManager cm, AccountMapper account, PeopleAccountMapper people,
            CurrencyMapper currency, PeopleGroupMapper group, BalanceMapper bal)
    {        
        this.cm = cm;
        this.account = account;
        this.people = people;
        this.currency = currency;
        this.group = group;
        this.bal = bal;
    }
    
    /**
     * Creates a new People Account
     * @param name Account Name
     * @param openBalance Opening Balance
     * @param currency Currency id
     * @param group Group id
     * @return Account id
     */
    public long create(String name, long openBalance, long currency, long group)
    {
        cm.open();
        long id = account.insert(name, AccountType.PEOPLE, openBalance);
        people.insert(id, openBalance, currency, group);
        cm.close();
        
        return id;
    }
    
    /**
     * Retrieve an account by id. Associated objects are partially initialized
     * @param id Account id
     * @return People Account Found
     * @throws AccountException with state {@link AccountException#NOT_EXISTS} if
     * account was not found with specified id
     */
    public PeopleAccount get(long id) throws AccountException
    {
        cm.open();
        peopleExists(id);        
        PeopleAccount acc = people.get(id);
        cm.close();
        
        return acc;
    }
    
    /**
     * Retrieve all accounts. Associated objects for each account are partially 
     * initialized
     * @return List of PeopleAccount objects
     */
    public List<PeopleAccount> getAll()
    {
        cm.open();
        List<PeopleAccount> result = people.getAll();
        cm.close();
        
        return result;
    }
    
    /**
     * Update Account
     * @param id Account id
     * @param name Account Name to update to
     * @param openBalance Opening Balance to update to
     * @param currId Currency id to update to
     * @param grpId Account Group id to update to
     * @throws AccountException with state {@link AccountException#NOT_EXISTS} if
     * account was not found with specified id
     * @throws CurrencyException with state {@link CurrencyException#NOT_EXISTS} if
     * currency was not found with specified id
     * @throws PeopleGroupException with state {@link PeopleGroupException#NOT_EXISTS} if
     * people group was not found with specified id
     */
    public void update(long id, String name, long openBalance, long currId,
            long grpId) throws AccountException, CurrencyException, PeopleGroupException
    {
        cm.open();
        peopleExists(id);
        
        if(!currency.exits(currId))
            throw new CurrencyException(CurrencyException.NOT_EXISTS,
                    "No currency exists with id: " + currId);
                
        groupExists(grpId);
        account.update(id, name);        
        long oldBal = people.getOpeningBalance(id);
        people.update(id, openBalance, currId, grpId);        
        if(oldBal != openBalance)
        {
            long diff = openBalance - oldBal;
            account.updateBalance(id, diff);
            bal.updateAll(id, diff);
        }
        cm.close();
    }
    
    /**
     * Retrieve balance of specified account. To retrieve all balances of accounts,
     * used {@link #getAll()}
     * @param id Account id
     * @return Account Balance
     * @throws AccountException with state {@link AccountException#NOT_EXISTS} if
     * account was not found with specified id
     */
    public long getBalance(long id) throws AccountException
    {
        cm.open();
        peopleExists(id);
        long result = account.getBalance(id);
        cm.close();
        
        return result;
    }
    
    /**
     * Create new group
     * @param name Group name
     * @return Group id generated
     */
    public long createGroup(String name)
    {
        cm.open();
        long result = group.insert(name);
        cm.close();
        
        return result;
    }
    
    /**
     * Edit existing Group
     * @param id Group id
     * @param name Group name
     * @throws PeopleGroupException with state {@link AccountGroupException#NOT_EXISTS}
     * if group was not found with specified id
     */
    public void editGroup(long id, String name) throws PeopleGroupException
    {
        cm.open();
        groupExists(id);
        group.update(id, name);
        cm.close();
    }
    
    /**
     * Checks if people account exists with specified id. Throws AccountException
     * with state {@link AccountException#NOT_EXISTS} if account was not found
     * with specified id
     * @param id Account id
     */
    private void peopleExists(long id)
    {
        if(!people.exists(id))
            throw new AccountException(AccountException.NOT_EXISTS,
                    "No account exists with id: " + id);
    }
    
    /**
     * Checks if people group exists with specified id. Throws PeopleGroupException
     * with state {@link PeopleGroupException#NOT_EXISTS} if group was not found
     * with specified id
     * @param id Account id
     */
    private void groupExists(long id)
    {
        if(!group.exists(id))
            throw new PeopleGroupException(PeopleGroupException.NOT_EXISTS,
                    "No people group exists with id: " + id);
    }
    
}
