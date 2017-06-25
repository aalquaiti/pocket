/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.entity;

import java.util.HashMap;
import java.util.Map;
import io.infinitecloud.pocket.util.EnumValue;

/**
 * Indicate classification of a category
 * @author Aymen Alquaiti
 * <p>Date: 13/02/2016</p>
 */
public enum CategoryClass implements EnumValue
{
    INCOME(1),    
    EXPENSE(2),    
    OTHER(3);
    
    private final long value;
    private static final Map<Long, CategoryClass> values = new HashMap<>();
    
    static
    {
        for(CategoryClass type: CategoryClass.values())
            values.put(type.getValue(), type);
    }
    
    private CategoryClass(long value)
    {
        this.value = value;
    }    
    
    public static CategoryClass fromValue(long value)
    {
        return values.get(value);
    }

    @Override
    public long getValue()
    {
        return value;
    }

    @Override
    public Map getMap()
    {
        return values;
    }

    @Override
    public String toString()
    {
        return "CategoryClass{" + "value=" + value + '}';
    }    
}
