/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package me.infinitecloud.pocket.entity;

/**
 * Entity Class
 * @author Aymen Alquaiti
 * Date: 30/01/2016
 */
public abstract class Account
{
    private long id;
    private String name;
    private int type;
    private long balance;
    
    public Account()
    {

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

    public int getType()
    {
        return type;
    }

    public void setType(int type)
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
    
    
}
