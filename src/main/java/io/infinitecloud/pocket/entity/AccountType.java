/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.entity;

import java.util.HashMap;
import java.util.Map;
import io.infinitecloud.pocket.util.EnumValue;

/**
 * Indicate types an account can be
 * @author Aymen Alquaiti
 * <p>Date: 30/01/2016</p>
 */
public enum AccountType implements EnumValue
{
    NORMAL(1),
    CATEGORY(2),
    SUB_CATEGORY(3),
    PEOPLE(4);
    
    private final long value;
    private static final Map<Long, AccountType> values = new HashMap<>();
    
    static
    {
        for(AccountType type: AccountType.values())
            values.put(type.getValue(), type);
    }
    
    private AccountType(long value)
    {
        this.value = value;
    }    
    
    public static AccountType fromValue(long value)
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
        return "AccountType{" + "value=" + value + '}';
    }
}
