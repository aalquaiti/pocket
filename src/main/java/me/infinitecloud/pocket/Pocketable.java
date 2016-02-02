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
 * Pocketable System
 * @author Aymen Alquaiti
 * Date: 31/01/2016
 */
public interface Pocketable
{
    /**
     * Access to Account Control
     * @return Account Control
     */
    public AccountControl Account();
    
    /**
     * Access to Category Control
     * @return Category Control
     */
    public CategoryControl Category();
    
    /**
     * Access to Category Control
     * @return Category Control
     */
    public TransactionControl Transaction();
    
    /**
     * Retrieve current version
     * @return long of current version
     * @throws PocketException with state {@link PocketException#NO_DB} if
     * database was not found
     */
    public long getVersion() throws PocketException;
    
    /**
     * Creates new database
     */
    public void create();
    
    /**
     * Upgrade database to a new version
     * @param oldVersion current version of the database
     * @param newVersion new version the database aims to upgrade to
     */
    public void upgrade(int oldVersion, int newVersion);
}
