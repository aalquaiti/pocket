/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.mapper;

import io.infinitecloud.pocket.entity.PocketConfig;

/**
 * Mapper for table pocket
 * @author Aymen Alquaiti
 * <p>Date: 15/02/2016<p>
 */
public interface PocketConfigMapper
{
    /**
     * Insert Configurations. Record id must always be set to 1. There should be only one
     * record
     * @param version Pocket System Version
     * @param primaryCurrency Primary Currency id
     * @param defaultCategory Default Category id
     */
    public void insert(long version, long primaryCurrency, long defaultCategory);
    
    /**
     * Update Primary Currency
     * @param primaryCurrency Pocket System Version     
     */
    public void update(long primaryCurrency);
    
    /**
     * Retrieve Pocket System Configurations
     * @return 
     */
    public PocketConfig getConfig();
}
