/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.entity;

import java.util.List;

/**
 * Category Entity
 * @author Aymen Alquaiti
 * <p>Date: 30/01/2016</p>
 */
public class Category extends Account
{
    private int cls;
    private List<SubCategory> subs;
    private long budget;
    private boolean hasBudget;    

    public Category()
    {
        super(AccountType.CATEGORY);
    }

    public int getClassification()
    {
        return cls;
    }

    /**
     * Set Category Classification
     * @param cls Category Classification
     * @see CategoryClass
     */
    public void setClassification(int cls)
    {
        this.cls = cls;
    }

    public List<SubCategory> getSubs()
    {
        return subs;
    }

    public void setSubs(List<SubCategory> subs)
    {
        this.subs = subs;
    }

    public long getBudget()
    {
        return budget;
    }

    public void setBudget(long budget)
    {
        this.budget = budget;
    }

    public boolean hasBudget()
    {
        return hasBudget;
    }

    public void setHasBudget(boolean hasBudget)
    {
        this.hasBudget = hasBudget;
    }
    
    @Override
    public void setType(AccountType type)
    {
        throw new UnsupportedOperationException("Cannot change account type");
    }
}
