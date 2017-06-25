/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.mapper;

import java.util.List;
import io.infinitecloud.pocket.entity.PeopleAccount;

/**
 * Mapper for table people_account
 * @author Aymen Alquaiti
 * <p>Date: 29/02/2016</p>
 */
public interface PeopleAccountMapper
{
    /**
     * Determines if people account exists with specified id
     * @param id Account id
     * @return true if exists with specified id
     */
    public boolean exists(long id);
    
    /**
     * Insert new record
     * @param id Account id
     * @param openBalance Opening Balance     
     * @param currId Currency id  
     * @param grpId People Group id
     */
    public void insert(long id, long openBalance, long currId, long grpId);
    
    /**
     * Retrieve People Account. Associated properties inherited from Account 
     * Class are also retrieved.
     * @param id Account id
     * @return People Account if found, null if not
     */
    public PeopleAccount get(long id);
    
    /**
     * Retrieve all People Accounts
     * @return List of Normal Accounts
     */
    public List<PeopleAccount> getAll();
    
    /**
     * Update People Account 
     * @param id Account id
     * @param openBalance Opening Balance to update
     * @param currId Currency id to update     
     * @param grpId People Group id
     */
    public void update(long id, long openBalance, long currId, long grpId);  
    
    /**
     * Retrieve opening balance of a people account
     * @param id Account id
     * @return Opening balance of specified people account
     */
    public long getOpeningBalance(long id);
}
