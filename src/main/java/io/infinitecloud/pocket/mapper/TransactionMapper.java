/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.mapper;

import java.sql.Date;
import io.infinitecloud.pocket.entity.Transaction;
import io.infinitecloud.pocket.entity.TransactionType;

/**
 * Mapper for table trans
 * @author Aymen Alquaiti
 * <p>Date: 16/02/2016</p>
 */
public interface TransactionMapper
{
    /**
     * Determine whether if transaction exists with specified id
     * @param id Transaction id
     * @return true if transaction exists with specified id
     */
    public boolean exists(long id);
        
    /**
     * Insert new record
     * @param date Transaction date
     * @param comment Transaction comment
     * @param payee Payee id. Provide null if non exists
     * @param type Transaction type. See {@link TransactionType}
     * @return 
     */
    public long insert(Date date, String comment, Long payee, TransactionType type);
    
    /**
     * Insert new transaction FX record 
     * @param id Transaction id
     * @param rate
     * @param pos
     * @param reciprocal     
     */
    public void insertFX(long id, long rate, int pos, boolean reciprocal);
    
    /**
     * Update record
     * @param id Transaction id
     * @param date Transaction date
     * @param comment Transaction comment
     * @param payee Payee id. Provide null if non exists
     * @param type Transaction type. See {@link TransactionType}     
     */
    public void update(long id, Date date, String comment, Long payee, TransactionType type);
    
    /**
     * Retrieve Transaction. Associated FX details (if exists) are also retrieved
     * @param id Transaction id
     * @return Transaction entity
     */
    public Transaction get(long id);
    
    /**
     * Remove transaction FX record
     * @param id Transaction id;
     */
    public void deleteFX(long id);
}
