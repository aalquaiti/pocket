/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.mapper;

/**
 * Mapper for table people_group
 * @author Aymen Alquaiti
 * <p>Date: 15/08/2016</p>
 */
public interface PeopleGroupMapper
{
    /**
     * Determines if PeopleGroup exists with specified id
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
     * Update AccountGroup.
     * @param id Group id
     * @param name Group name to update     
     */
    public void update(long id, String name);
}
