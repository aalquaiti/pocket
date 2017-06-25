/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.mapper;

import java.util.List;
import io.infinitecloud.pocket.entity.NormalAccount;

/**
 * Mapper for Table normal_account
 * @author Aymen Alquaiti
 * <p>Date: 03/02/2016</p>
 */
public interface NormalAccountMapper
{
    /**
     * Determines if normal account exists with specified id
     * @param id Account id
     * @return true if exists with specified id
     */
    public boolean exists(long id);
    
    /**
     * Insert new record
     * @param id Account id
     * @param openBalance Opening Balance
     * @param closed Whether closed 
     * @param currId Currency ID
     * @param grpId Group ID. Provide null if non exists
     */
    public void insert(long id, long openBalance, boolean closed, long currId, Long grpId);
    
    /**
     * Retrieve Normal Account. Associated properties inherited from Account 
     * Class are also retrieved.
     * @param id Account id
     * @return Normal Account if found, null if not
     */
    public NormalAccount get(long id);
    
    /**
     * Retrieve all Normal Accounts
     * @return List of Normal Accounts
     */
    public List<NormalAccount> getAll();
    
    /**
     * Update Normal Account 
     * @param id Account id
     * @param openBalance Opening Balance to update
     * @param currId Currency id to update
     * @param grpId Group id to update
     */
    public void update(long id, long openBalance, long currId, Long grpId);  
    
    /**
     * Update account to close or not
     * @param id Account id
     * @param closed Whether to close or not     
     */
    public void close(long id, boolean closed);    
    
    /**
     * Determines if account is closed
     * @param id Account id
     * @return true if account is closed
     */
    public boolean isClosed(long id);
    
    /**
     * Retrieve opening balance of an account
     * @param id Account id
     * @return Opening balance of specified normal account
     */
    public long getOpeningBalance(long id);
}
