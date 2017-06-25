/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.entity;

import java.util.List;

/**
 * People Group Entity
 * @author Aymen Alquaiti
 * <p>Date: 15/08/2016</p>
 */
public class PeopleGroup
{
    private long id;
    private String name;
    private List<PeopleAccount> accounts;    
    
    public PeopleGroup()
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
    
    public List<PeopleAccount> getAccounts()
    {
        return accounts;
    }

    public void setAccounts(List<PeopleAccount> accounts)
    {
        this.accounts = accounts;
    }
}
