/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.entity;

/**
 * Normal Account Entity
 * @author Aymen Alquaiti
 * <p>Date: 30/01/2016</p>
 */
public class NormalAccount extends Account
{
    private long openBalance;
    private boolean closed;
    private Currency currency;
    private AccountGroup group;
    
    public NormalAccount()
    {
        super(AccountType.NORMAL);
    }

    public long getOpenBalance()
    {
        return openBalance;
    }

    public void setOpenBalance(long openBalance)
    {
        this.openBalance = openBalance;
    }

    public boolean isClosed()
    {
        return closed;
    }

    public void setClosed(boolean closed)
    {
        this.closed = closed;
    }

    public Currency getCurrency()
    {
        return currency;
    }

    public void setCurrency(Currency currency)
    {
        this.currency = currency;
    }

    public AccountGroup getGroup()
    {
        return group;
    }

    public void setGroup(AccountGroup group)
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
        return super.toString() +"\n" + 
                "NormalAccount{" + "openBalance=" + openBalance + ", closed=" +
                    closed + ", currency=" + currency + ", group=" + group + '}';
    }
}
