/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.control;

import io.infinitecloud.pocket.PocketFactory;
import io.infinitecloud.pocket.entity.AccountType;
import io.infinitecloud.pocket.exception.CategoryException;
import io.infinitecloud.pocket.exception.SubCategoryException;
import io.infinitecloud.pocket.mapper.AccountMapper;
import io.infinitecloud.pocket.mapper.CategoryMapper;
import io.infinitecloud.pocket.mapper.SubCategoryMapper;
import io.infinitecloud.pocket.entity.CategoryClass;
import io.infinitecloud.pocket.util.ConnectionManager;

/**
 * Provides Manipulation methods for all functions related to Category
 * @author Aymen Alquaiti
 * <p>Date: 31/01/2016</p>
 */
public class CategoryControl
{
    private final ConnectionManager cm;
    private final AccountMapper account;
    private final CategoryMapper cat;
    private final SubCategoryMapper sub;
    
    public CategoryControl(ConnectionManager cm, AccountMapper account, 
            CategoryMapper cat, SubCategoryMapper sub)
    {
        this.cm = cm;
        this.account = account;
        this.cat = cat;
        this.sub = sub;
    }
    
    /**
     * Creates a new category
     * @param name Category name. Must be unique.
     * @param cls Category Classification. See {@link CategoryClass}
     * @return Category id     
     */
    public long create(String name, CategoryClass cls)
    {
        cm.open();
        long id = account.insert(name, AccountType.CATEGORY, 0L);
        cat.insert(id, cls, 0L, false);
        cm.close();
        
        return id;
    }
    
    /**
     * Creates a new sub category
     * @param catId Category id
     * @param name Sub Category name
     * @return Sub Category id
     * @throws CategoryException with state {@link CategoryException#NOT_EXISTS} if
     * category does not exists with specified id     
     */
    public long createSub(long catId, String name)throws CategoryException
    {
        cm.open();
        catExists(catId);
        long id = account.insert(name, AccountType.SUB_CATEGORY, 0L);
        sub.insert(id, catId);
        cm.close();
        
        return id;
    }
            
    
    /**
     * Update Category
     * @param id Category id
     * @param cls Category classification to update
     * @param name Category name to update
     * @throws CategoryException with state {@link CategoryException#NOT_EXISTS} 
     * if category does not exists with specified id
     */
    public void update(long id, CategoryClass cls, String name) throws CategoryException
    {
        cm.open();
        catExists(id);
        account.update(id, name);
        cat.update(id, cls);
        cm.close();
    }
    
    /**
     * Update Sub Category
     * @param id Sub Category id
     * @param catId Category id to update     
     * @throws SubCategoryException with state {@link SubCategoryException#NOT_EXISTS} if
     * category does not exists with specified id
     */
    public void updateSub(long id, long catId) throws SubCategoryException
    {
        cm.open();
        subExists(id);
        catExists(catId);
        sub.update(id, catId);
        cm.close();
    }
    
    /**
     * Delete Category
     * @param id Category id
     * @throws CategoryException with state {@link CategoryException#NOT_EXISTS} if
     * category does not exists with specified id
     */
    public void delete(long id) throws CategoryException
    {
        catExists(id);
        // TODO - Implement it
        throw new UnsupportedOperationException("Not implemented yet");
        
    }
    
    /**
     * Delete Sub Category
     * @param id Sub Category id
     * @throws SubCategoryException with state {@link SubCategoryException#NOT_EXISTS} if
     * category does not exists with specified id
     */
    public void deleteSub(long id) throws SubCategoryException
    {
        subExists(id);
        // TODO - Implement it
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * Set budget for a category
     * @param id Category id
     * @param budget Budget amount
     * @throws CategoryException with state {@link CategoryException#NOT_EXISTS} if
     * category does not exists with specified id
     */
    public void setBudget(long id, long budget) throws CategoryException
    {
        cm.open();
        catExists(id);        
        cat.setBudget(id, budget);
        cm.close();
    }
    
    /**
     * Remove budget of a category
     * @param id Category id
     * @throws CategoryException with state {@link CategoryException#NOT_EXISTS} if
     * category does not exists with specified id
     */
    public void removeBudget(long id) throws CategoryException
    {
        cm.open();
        catExists(id);
        cat.removeBudget(id);
        cm.close();
    }
    
    /**
     * Checks if category exists with specified id. Throws CategoryException
     * with state {@link CategoryException#NOT_EXISTS} if category was not found
     * with specified id
     * @param id Category id
     */
    private void catExists(long id)
    {
        if(!cat.exists(id))
            throw new CategoryException(CategoryException.NOT_EXISTS,
                    "No category exists with id: " + id);
    }
    
    /**
     * Checks if category exists with specified id. Throws SubCategoryException
     * with state {@link SubCategoryException#NOT_EXISTS} if sub category was not found
     * with specified id
     * @param id Category id
     */
    private void subExists(long id)
    {
        if(!sub.exists(id))
            throw new SubCategoryException(SubCategoryException.NOT_EXISTS,
                    "No sub category exists with id: " + id);
    }
}
