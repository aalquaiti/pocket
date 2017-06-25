/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.entity;

import java.util.HashMap;
import java.util.Map;
import io.infinitecloud.pocket.util.EnumValue;

/**
 * Indicates types a transaction can be
 * @author Aymen Alquaiti
 * <p>Date: 09/02/2016</p>
 */
public enum TransactionType implements EnumValue
{
    // TODO - change to enum
    INCOME(1),    
    EXPENSE(2),
    TRANSFER(3),
    TRANFER_DELETED(4),
    LEND(5),
    BORROW(6);
    
    private final long value;
    private static final Map<Long, TransactionType> values = new HashMap<>();
    
    static
    {
        for(TransactionType type: TransactionType.values())
            values.put(type.getValue(), type);
    }
    
    private TransactionType(long value)
    {
        this.value = value;
    }    
    
    public static TransactionType fromValue(long value)
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
}
