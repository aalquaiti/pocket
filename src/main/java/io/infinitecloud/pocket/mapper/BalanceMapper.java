/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.mapper;

import java.sql.Date;

/**
 * Mapper for table balance
 * @author Aymen Alquaiti
 * <p>Date; 17/02/2016</p>
 */
public interface BalanceMapper
{
    /**
     * Determine if a balance record for an account on a specified date
     * @param id Account id
     * @param date Balance date
     * @return true if any balance exists for an account
     */
    public boolean exists(long id, Date date);
    
    /**
     * Retrieve Previous balance before given date
     * @param id Account id
     * @param date Date to search for balance before it
     * @return Last previous balance if found, null if not found
     */
    public Long getPreviousBalance(long id, Date date);
    
    /**
     * Insert new record. The difference between previous balance and updated 
     * balance will be persisted.
     * E.g if If previous balance entries was 200,  and added difference 
     * is 300, the new balance will be 500. 
     * @param id Account id
     * @param date Balance date. Only last day of a given month will be persisted.
     * E.g: if given date is 16-April-2016, it will be persisted as 30-April-2016
     * @param diff balance difference to add
     */
    public void insert(long id, Date date, long diff);
    
    /**
     * Update balance. Balance is updated by adding difference
     * to previous Balance.E.g: If previous balance was 200,
     * and added difference is 300, the new balance  will be 500
     * @param id Account id
     * @param date Balance date
     * @param diff Difference to add to previous balance for each record
     */
    public void update(long id, Date date, long diff);
    
    /**
     * Update all balance entries. Each balance is updated by adding difference
     * to previous Balance.E.g: If one of previous balance entries was 200,
     * and added difference is 300, the new balance entry will be 500
     * @param id Account id
     * @param diff Difference to add to previous balance for each record
     */
    public void updateAll(long id, long diff);
    
    /**
     * Update all balance after the given date. Each balance is updated by adding difference
     * to previous Balance.E.g: If one of previous balance entries was 200,
     * and added difference is 300, the new balance entry will be 500
     * @param id Account id
     * @param date Date to search records after
     * @param diff Difference to add to previous balance for each record     
     */
    public void updateFuture(long id, Date date, long diff);
}
