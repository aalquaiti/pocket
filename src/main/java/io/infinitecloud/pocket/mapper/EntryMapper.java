/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.mapper;

import java.util.List;
import io.infinitecloud.pocket.entity.Entry;

/**
 * Mapper for table entry
 * @author Aymen Alquaiti
 * <p>Date: 16/02/2016</p>
 */
public interface EntryMapper
{
    /**
     * Determine whether if entry exists with specified id
     * @param id Entry id
     * @return true if entry exists with specified id
     */
    public boolean exists(long id);
    
    /**
     * Insert new record
     * @param trnId Transaction id
     * @param accId Account id
     * @param amount Entry amount
     * @return Entry id generated
     */
    public long insert(long trnId, long accId, long amount);
    
    /**
     * Update record
     * @param id Entry id
     * @param trnId Transaction id
     * @param accId Account id
     * @param amount Entry Amount
     */
    public void update (long id, long trnId, long accId, long amount);
    
    /**
     * Retrieve all entries associated with a transaction. Each account entity
     * is fully initialized
     * @param trnId Transaction id
     * @return Array of Entry objects associated with a transaction, null if non
     * found
     */
    public List<Entry> getForTransaction(long trnId);
    
    /**
     * Delete record
     * @param id Entry id
     */
    public void delete(long id);
}
