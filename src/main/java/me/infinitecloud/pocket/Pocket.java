/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */
package me.infinitecloud.pocket;

import me.infinitecloud.pocket.control.AccountControl;
import me.infinitecloud.pocket.control.CategoryControl;
import me.infinitecloud.pocket.control.TransactionControl;
import me.infinitecloud.pocket.exception.PocketException;

/**
 * Singleton that provides access to pocket system
 * @author Aymen Alquaiti
 * Date: 02/02/2016
 */
public class Pocket implements Pocketable
{
    private static Pocketable INSTANCE;
    
    private Pocket()
    {
        INSTANCE = null;
    }
    
    /**
     * Initializes Pocket System
     * @param instance Initialized instance
     */
    public static void setInstance(Pocketable instance)
    {
        INSTANCE = instance;
    }
    
    public static Pocketable getInstance()
    {
        return INSTANCE;
    }

    @Override
    public AccountControl Account()
    {
        return INSTANCE.Account();
    }

    @Override
    public CategoryControl Category()
    {
        return INSTANCE.Category();
    }

    @Override
    public TransactionControl Transaction()
    {
        return INSTANCE.Transaction();
    }

    @Override
    public long getVersion() throws PocketException
    {
        return INSTANCE.getVersion();
    }

    @Override
    public void create()
    {
        INSTANCE.create();
    }

    @Override
    public void upgrade(int oldVersion, int newVersion)
    {
        INSTANCE.upgrade(oldVersion, newVersion);
    }
}
