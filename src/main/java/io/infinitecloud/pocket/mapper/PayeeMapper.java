/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.mapper;

import io.infinitecloud.pocket.entity.Payee;

/**
 * Mapper for table payee
 * @author Aymen Alquaiti
 * <p>Date: 16/02/2016
 */
public interface PayeeMapper
{
    /**
     * Determine whether if Payee exists with specified id
     * @param id Payee id
     * @return true if payee exists with specified id
     */
    public boolean exists(long id);
    
    /**
     * Insert new record
     * @param name Payee name
     * @return Payee id generated
     */
    public long insert(String name);
    
    /**
     * Retrieve Payee Entity
     * @param id Payee id
     * @return Payee Entity
     */
    public Payee get(long id);
}
