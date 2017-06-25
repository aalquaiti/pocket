    /*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.control;

import java.util.List;
import io.infinitecloud.pocket.entity.AccountType;
import io.infinitecloud.pocket.entity.NormalAccount;
import io.infinitecloud.pocket.exception.AccountException;
import io.infinitecloud.pocket.exception.CurrencyException;
import io.infinitecloud.pocket.exception.AccountGroupException;
import io.infinitecloud.pocket.mapper.AccountGroupMapper;
import io.infinitecloud.pocket.mapper.AccountMapper;
import io.infinitecloud.pocket.mapper.BalanceMapper;
import io.infinitecloud.pocket.mapper.CurrencyMapper;
import io.infinitecloud.pocket.mapper.NormalAccountMapper;
import io.infinitecloud.pocket.util.ConnectionManager;

/**
 * Provides Manipulation methods for all functions related to Normal account
 * @author Aymen Alquaiti
 * Date: 31/01/2016
 */
public class AccountControl
{    
    private final ConnectionManager cm;
    private final AccountMapper account;
    private final NormalAccountMapper normal;
    private final CurrencyMapper currency;
    private final AccountGroupMapper group;
    private final BalanceMapper bal;    
    
    /**
     * Initializes Control     
     * @param cm Connection Manager
     * @param account AccountMapper
     * @param normal NormalAccountMapper
     * @param currency CurrencyMapper
     * @param group GroupMapper
     * @param bal BalanceMapper
     */
    public AccountControl(ConnectionManager cm, AccountMapper account, NormalAccountMapper normal,
            CurrencyMapper currency, AccountGroupMapper group, BalanceMapper bal)
    {        
        this.cm = cm;
        this.account = account;
        this.normal = normal;
        this.currency = currency;
        this.group = group;
        this.bal = bal;
    }
    
    /**
     * Creates a new normal Account
     * @param name Account Name
     * @param openBalance Opening Balance
     * @param currency Currency id
     * @param group Group id. if no group exists, provide null
     * @return Account id
     */
    public long create(String name, long openBalance, long currency, Long group)
    {
        cm.open();
        long id = account.insert(name, AccountType.NORMAL, openBalance);        
        normal.insert(id, openBalance, false, currency, group);
        cm.close();
        
        return id;
    }
    
    /**
     * Retrieve an account by id. Associated objects are partially initialized
     * @param id Account id
     * @return Normal Account Found
     * @throws AccountException with state {@link AccountException#NOT_EXISTS} if
     * account was not found with specified id
     */
    public NormalAccount get(long id) throws AccountException
    {
        cm.open();
        normalExists(id);        
        NormalAccount acc = normal.get(id);
        cm.close();
        
        return acc;
    }
    
    /**
     * Retrieve all accounts. Associated objects for each account are partially 
     * initialized
     * @return List of NormalAccount objects
     */
    public List<NormalAccount> getAll()
    {
        cm.open();
        List<NormalAccount> result = normal.getAll();
        cm.close();
        
        return result;
    }
    
    /**
     * Update Account
     * @param id Account id
     * @param name Account Name to update to
     * @param openBalance Opening Balance to update to
     * @param currId Currency id to update to
     * @param grpId Account Group id to update to. Use zero or -1 to link to no
     * account
     * @throws AccountException with state {@link AccountException#NOT_EXISTS} if
     * account was not found with specified id
     * @throws CurrencyException with state {@link CurrencyException#NOT_EXISTS} if
     * currency was not found with specified id
     * @throws AccountGroupException with state {@link AccountGroupException#NOT_EXISTS} if
     * account group was not found with specified id
     */
    public void update(long id, String name, long openBalance, long currId,
            Long grpId) throws AccountException, CurrencyException, AccountGroupException
    {
        cm.open();
        normalExists(id);
        
        if(!currency.exits(currId))
            throw new CurrencyException(CurrencyException.NOT_EXISTS,
                    "No currency exists with id: " + currId);
        
        if(grpId!= null)
            groupExists(grpId);
                
        
        account.update(id, name);        
        long oldBal = normal.getOpeningBalance(id);
        normal.update(id, openBalance, currId, grpId);        
        if(oldBal != openBalance)
        {
            long diff = openBalance - oldBal;
            account.updateBalance(id, diff);
            bal.updateAll(id, diff);
        }
        cm.close();
    }
    
    /**
     * Close account
     * @param id Account id
     * @throws AccountException with state {@link AccountException#NOT_EXISTS} if
     * account was not found with specified id
     */
    public void close(long id) throws AccountException
    {
        cm.open();
        normalExists(id);        
        normal.close(id, true);
        cm.close();
    }
    
    /**
     * Reopen account
     * @param id Account id
     * @throws AccountException with state {@link AccountException#NOT_EXISTS} if
     * account was not found with specified id
     */
    public void open(long id) throws AccountException
    {
        cm.open();
        normalExists(id);
        normal.close(id, false);
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
        normalExists(id);
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
     * @throws AccountGroupException with state {@link AccountGroupException#NOT_EXISTS}
     * if group was not found with specified id
     */
    public void editGroup(long id, String name) throws AccountGroupException
    {
        cm.open();
        groupExists(id);
        group.update(id, name);
        cm.close();
    }
    
    /**
     * Delete existing Group. Associated Accounts' group are set to null
     * @param id Group id
     * @throws AccountGroupException with state {@link AccountGroupException#NOT_EXISTS}
     * if group was not found with specified id
     */
    public void deleteGroup(long id) throws AccountGroupException
    {
        cm.open();
        groupExists(id);
        group.delete(id);
        cm.close();
    }
    
    /**
     * Checks if normal account exists with specified id. Throws AccountException
     * with state {@link AccountException#NOT_EXISTS} if account was not found
     * with specified id
     * @param id Account id
     */
    private void normalExists(long id)
    {
        if(!normal.exists(id))
            throw new AccountException(AccountException.NOT_EXISTS,
                    "No account exists with id: " + id);
    }
    
    /**
     * Checks if account group exists with specified id. Throws AccountGroupException
     * with state {@link AccountGroupException#NOT_EXISTS} if group was not found
     * with specified id
     * @param id Account id
     */
    private void groupExists(long id)
    {
        if(!group.exists(id))
            throw new AccountGroupException(AccountGroupException.NOT_EXISTS,
                    "No account group exists with id: " + id);
    }
}
