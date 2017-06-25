/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */
package io.infinitecloud.pocket;

/**
 * Singleton that provides access to pocket system
 * @author Aymen Alquaiti
 * Date: 02/02/2016
 */
public class PocketFactory
{
    private static Pocket INSTANCE = null;
       
    
    /**
     * Initializes Pocket System
     * @param instance Initialized instance
     */
    public static void setInstance(Pocket instance)
    {
        INSTANCE = instance;
    }
    
    /**
     * Retrieve singleton instance
     * @return Singleton instance found
     */
    public static Pocket getInstance()
    {
        return INSTANCE;
    }
}
