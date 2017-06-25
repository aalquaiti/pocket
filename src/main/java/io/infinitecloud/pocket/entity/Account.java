/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.entity;

/**
 * Account Entity
 * @author Aymen Alquaiti
 * <p>Date: 30/01/2016</p>
 */
public class Account
{
    private long id;
    private String name;
    private AccountType type;
    private long balance;
    
    public Account()
    {
        
    }
    
    public Account(AccountType type)
    {
        this.type = type;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public AccountType getType()
    {
        return type;
    }

    public void setType(AccountType type)
    {
        this.type = type;
    }

    public long getBalance()
    {
        return balance;
    }

    public void setBalance(long balance)
    {
        this.balance = balance;
    }

    @Override
    public String toString()
    {
        return "Account{" + "id=" + id + ", name=" + name + ", type=" + type + ", balance=" + balance + '}';
    }
}
