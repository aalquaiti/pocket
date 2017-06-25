/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */
package io.infinitecloud.pocket.util;

import java.util.Map;

/**
 * Helps in obtaining integer value from enum constant
 * @author Aymen Alquaiti
 * <p>Date: 06/05/2016</p> 
 * @param <T> Enum Type
 */
public interface EnumValue<T>
{    
    /**
     * Retrieve integer value of an enum
     * @return Long value of enum
     */
    long getValue();
    
    /**
     * Returns an internal mapped used to cache enums with their values
     * @return Map of enums and their values
     */
    Map<Long,T> getMap();
}
