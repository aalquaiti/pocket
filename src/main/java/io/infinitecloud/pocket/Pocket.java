/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket;

import io.infinitecloud.pocket.entity.PocketConfig;
import io.infinitecloud.pocket.control.AccountControl;
import io.infinitecloud.pocket.control.CategoryControl;
import io.infinitecloud.pocket.control.PeopleControl;
import io.infinitecloud.pocket.control.TransactionControl;
import io.infinitecloud.pocket.util.ConnectionManager;

/**
 * Pocket System
 * @author Aymen Alquaiti
 * Date: 31/01/2016
 */
public interface Pocket
{
    /**
     * Access to Account Control
     * @return Account Control
     */
    public AccountControl Account();
    
    /**
     * Access to People Control
     * @return People Control
     */
    public PeopleControl People();
    
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
     * Access to system properties and cash
     * @return PocketConfig
     */
    public PocketConfig Config();
    
    /**
     * Retrieve connection control
     * @return 
     */
    public ConnectionManager ConnectionManager();
    
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
    
    /**
     * Initializes system and makes it ready to operate. Must be called only if
     * system is already created or upgraded
     */
    public void init();
}
