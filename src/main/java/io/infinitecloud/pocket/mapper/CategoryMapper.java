/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.mapper;

import io.infinitecloud.pocket.entity.CategoryClass;

/**
 * Mapper for table category
 * @author Aymen Alquaiti
 * <p>Date: 07/02/2016</p>
 */
public interface CategoryMapper
{
    /**
     * Determine whether if category exists with specified id
     * @param id Category id
     * @return true if category exists with specified id
     */
    public boolean exists(long id);
    
    /**
     * Insert new record. Budget is assumed as zero and not enabled
     * @param id Category id
     * @param cls Category classification
     * @param budget budget amount
     * @param hasBudget Whether category has budget or not
     */
    public void insert(long id, CategoryClass cls,long budget, boolean hasBudget);
    
    /**
     * Update category 
     * @param id category id
     * @param cls Category budget
     */
    public void update(long id, CategoryClass cls);
    
    /**
     * Set a budget to category. This will set budget as specified and enabling
     * it
     * @param id Category id
     * @param budget Budget amount
     */
    public void setBudget(long id, long budget);
    
    /**
     * Remove budget from a category. This will set budget as zero and disable it
     * @param id Category id
     */
    public void removeBudget(long id);
}
