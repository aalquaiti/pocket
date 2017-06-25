/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.entity;

/**
 * SubCategory Entity
 * @author Aymen Alquaiti
 * <p>Date: 31/01/2016</p>
 */
public class SubCategory extends Account
{
    private Category category;
    
    public SubCategory()
    {
        super(AccountType.SUB_CATEGORY);
    }

    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }
    
    @Override
    public void setType(AccountType type)
    {
        throw new UnsupportedOperationException("Cannot change account type");
    }

    @Override
    public String toString()
    {
        return "SubCategory{" + "category=" + category + '}';
    }
}
