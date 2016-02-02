/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package me.infinitecloud.pocket.control;

import java.sql.Date;
import me.infinitecloud.pocket.entity.History;
import me.infinitecloud.pocket.exception.AccountException;

/**
 * Provides Manipulation methods for all functions related to transaction and
 * entries
 * @author Aymen Alquaiti
 * Date: 01/02/2016
 */
public interface TransactionControl
{
    /**
     * Retrieve History of an account. Number of entries to retrieve, 
     * or date (from - to) is dependent on Pocket system configuration
     * @param account Account id
     * @return History Object.
     * @throws AccountException with state {@link AccountException#NOT_EXISTS} if
     * account was not found with specified id
     */
    public History getHistory(long account) throws AccountException;
    
    /**
     * Retrieve History of an account. Number of entries returned is as specified.
     * Entries are returned according to their date, the newer the higher the priority.
     * This method will return future entries.
     * @param account Account id
     * @param entries Number of entries to return
     * @return History Object
     * @throws AccountException with state {@link AccountException#NOT_EXISTS} if
     * account was not found with specified id
     */
    public History getHistory(long account, int entries) throws AccountException;
    
    /**
     * Retrieve History of an account. Retrieve entries from a specified date.
     * Entries will be returned only for that specified date.
     * @param date entries date
     * @return History Object
     * @throws AccountException with state {@link AccountException#NOT_EXISTS} if
     * account was not found with specified id
     */
    public History getHistory(Date date) throws AccountException;
    
    /**
     * Retrieve History of an account. Retrieve entries from a specified date to 
     * a specified date.
     * @param account Account id
     * @param from From date
     * @param to To date
     * @return History object
     * @throws AccountException with state {@link AccountException#NOT_EXISTS} if
     * account was not found with specified id
     * @throws IllegalArgumentException if (to) date is less than (from) date
     */
    public History getHistory(long account, Date from, Date to) throws AccountException;
}
