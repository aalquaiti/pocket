/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.util;

/**
 * Used to manipulate connection to database
 * @author Aymen Alquaiti
 * <p>Date: 30/01/2016</p>
 */
public interface ConnectionManager
{    
    /**
     * Retrieve connection to database
     * @return Object represents connection to database
     */
    public abstract Object getConnection();
    
    /**
     * Opens new connection to database     
     */
    public abstract void open();
    
    /**
     * Closes connection to database
     */
    public abstract void close();
}
