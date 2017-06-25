/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Account Group Entity. For normal accounts only
 * @author Aymen Alquaiti
 * <p>Date: 30/01/2016</p>
 */
public class AccountGroup
{
    private long id;
    private List<NormalAccount> accounts;    
    
    public AccountGroup()
    {
        accounts = new ArrayList<>();
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }
    
    public List<NormalAccount> getAccounts()
    {
        return accounts;
    }

    public void setAccounts(List<NormalAccount> accounts)
    {
        this.accounts = accounts;
    }
}
