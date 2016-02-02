/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package me.infinitecloud.pocket.entity;

import java.util.List;

/**
 * Category Entity
 * @author Aymen Alquaiti
 * Date: 30/01/2016
 */
public class Category extends Account
{
    private List<SubCategory> subs;
    private long budget;
    private boolean hasBudget;

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
    public void setType(int type)
    {
        if(type == AccountType.INCOME_CATEGORY || 
                type == AccountType.EXPENSE_CATEGORY)
            super.setType(type);
        else
            throw new UnsupportedOperationException("Incorrent Category type");
    }
}
