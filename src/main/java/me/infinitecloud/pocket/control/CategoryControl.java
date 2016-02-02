/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package me.infinitecloud.pocket.control;

import me.infinitecloud.pocket.exception.CategoryException;
import me.infinitecloud.pocket.exception.SubCategoryException;

/**
 * Provides Manipulation methods for all functions related to Category
 * @author Aymen Alquaiti
 * Date: 31/01/2016
 */
public interface CategoryControl
{
    /**
     * Creates a new category
     * @param name Category name. Must be unique.
     * @return Category id
     * @throws CategoryException with state {@link CategoryException#DUPLICATE}
     * if a category already exists with specified name
     */
    public long create(String name) throws CategoryException;
    
    /**
     * Creates a new sub category
     * @param category Category id
     * @param name Sub Category name
     * @return Sub Category id
     * @throws CategoryException with state {@link CategoryException#NOT_EXISTS} if
     * category does not exists with specified id     
     */
    public long createSub(long category, String name)throws CategoryException;
            
    
    /**
     * Update Category
     * @param id Category id
     * @param name Category name to update
     * @throws CategoryException with state: <br />
     * 1. {@link CategoryException#NOT_EXISTS} if category does not exists with specified id<br />
     * 2. {@link CategoryException#DUPLICATE} if a category already exists with specified name
     */
    public void update(long id, String name) throws CategoryException;
    
    /**
     * Update Sub Category
     * @param id Sub Category id
     * @param name Sub Category name to update to
     * @throws SubCategoryException with state {@link SubCategoryException#NOT_EXISTS} if
     * category does not exists with specified id
     */
    public void updateSub(long id, String name) throws SubCategoryException;
    
    /**
     * Delete Category
     * @param id Category id
     * @throws CategoryException with state {@link CategoryException#NOT_EXISTS} if
     * category does not exists with specified id
     */
    public void delete(long id) throws CategoryException;
    
    /**
     * Delete Sub Category
     * @param id Sub Category id
     * @throws SubCategoryException with state {@link SubCategoryException#NOT_EXISTS} if
     * category does not exists with specified id
     */
    public void deleteSub(long id) throws SubCategoryException;
    
    /**
     * Set budget for a category
     * @param id Category id
     * @param budget Budget amount
     * @throws CategoryException with state {@link CategoryException#NOT_EXISTS} if
     * category does not exists with specified id
     */
    public void setBudget(long id, long budget) throws CategoryException;
    
    /**
     * Remove budget of a category
     * @param id Category id
     * @throws CategoryException with state {@link CategoryException#NOT_EXISTS} if
     * category does not exists with specified id
     */
    public void removeBudget(long id) throws CategoryException;
}
