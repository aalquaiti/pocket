/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.mapper;

/**
 * Mapper for table account_group
 * @author Aymen Alquaiti
 * <p>Date: 06/02/2016</p>
 */
public interface AccountGroupMapper
{
    /**
     * Determines if AccountGroup exists with specified id
     * @param id AccountGroup id
     * @return true if AccountGroup exists with specified id
     */
    public boolean exists(long id);
    
    /**
     * Insert new record 
     * @param name Group name
     * @return Group id generated
     */
    public long insert(String name);    
    
    /**
     * Update AccountGroup
     * @param id Group id
     * @param name Group name to update
     */
    public void update(long id, String name);
    
    /**
     * Delete record
     * @param id Group id
     */
    public void delete(long id);
}
