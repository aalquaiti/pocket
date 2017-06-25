/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.mapper;

import java.util.List;

/**
 * Mapper for table sub_category
 * @author Aymen Alquaiti
 * <p>Date: 07/02/2016</p>
 */
public interface SubCategoryMapper
{
    /**
     * Determine whether if sub category exists with specified id
     * @param id Category id
     * @return true if sub category exists with specified id
     */
    public boolean exists(long id);
    
    /**
     * Insert new record
     * @param id Sub Category id
     * @param catId Category id     
     * @return Sub Category generated id
     */
    public long insert(long id, long catId);
    
    /**
     * Update sub category 
     * @param id sub category id
     * @param catId Category id to update     
     */
    public void update(long id, long catId);
    
    /**
     * Retrieve a list of all sub categories linked to specified category
     * @param catId Category id
     * @return List of IDs for sub categories linked to specified category
     */
    public List<Long> getAllSubs(long catId);
}
