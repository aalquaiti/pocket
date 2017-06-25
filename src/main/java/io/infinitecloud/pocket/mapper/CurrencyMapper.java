/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.mapper;

import java.util.Map;
import io.infinitecloud.pocket.entity.Currency;

/**
 * Mapper for table currency
 * @author Aymen Alquaiti
 * <p>Date: 06/02/2016</p>
 */
public interface CurrencyMapper
{
    /**
     * Determines if currency exists with specified id
     * @param id Currency id
     * @return true if currency exists with specified id
     */
    public boolean exits(long id);
    
    /**
     * Retrieve all currencies marked as used
     * @return Map of Long and currency. Each long is the currency id. Helps in 
     * searching for desired currency
     */
    public Map<Long, Currency> getAllUsed();
    
    /**
     * Retrieve Currency of specified id
     * @param id Currency id
     * @return Currency object
     */
    public Currency get(long id);
}
