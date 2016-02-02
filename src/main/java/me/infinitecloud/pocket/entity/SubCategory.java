/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package me.infinitecloud.pocket.entity;

/**
 * SubCategory Entity
 * @author Aymen Alquaiti
 * Date: 31/01/2016
 */
public class SubCategory extends Account
{
    private Category category;
    
    public SubCategory()
    {
        
    }

    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }
}
