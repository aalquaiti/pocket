/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package me.infinitecloud.pocket.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Account Group Entity. For normal accounts only
 * @author Aymen Alquaiti
 * Date: 30/01/2016
 */
public class AccountGroup
{
    private List<NormalAccount> accounts;
    
    public AccountGroup()
    {
        accounts = new ArrayList<>();
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
