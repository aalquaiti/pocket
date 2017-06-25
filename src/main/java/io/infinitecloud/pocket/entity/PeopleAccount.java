/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.entity;

/**
 * People Account entity
 * @author Aymen Alquaiti
 * <p>Date: 10/02/2016</p>
 */
public class PeopleAccount extends Account
{
    private long openBalance;
    private Currency currency;
    private PeopleGroup group;
    
    public PeopleAccount()
    {
        super(AccountType.PEOPLE);
    }

    public long getOpenBalance()
    {
        return openBalance;
    }

    public void setOpenBalance(long openBalance)
    {
        this.openBalance = openBalance;
    }

    public Currency getCurrency()
    {
        return currency;
    }

    public void setCurrency(Currency currency)
    {
        this.currency = currency;
    }

    public PeopleGroup getGroup()
    {
        return group;
    }

    public void setGroup(PeopleGroup group)
    {
        this.group = group;
    }    
    
    @Override
    public void setType(AccountType type)
    {
        throw new UnsupportedOperationException("Cannot change account type");
    }
    
    @Override
    public String toString()
    {
        return super.toString() + "\n" + 
                "PeopleAccount{" + "openBalance=" + openBalance +
                                ", currency=" + currency + '}';
    }    
}
