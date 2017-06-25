/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.util;

import io.infinitecloud.pocket.exception.AmountException;

/**
 * Utility to check certain criteria
 * @author Aymen Alquaiti
 * <p>Date: 10/11/2016</p>
 */
public class Checker
{
    /**
     * 
     * @param amount 
     */
    public static void withinRange(long amount) throws AmountException
    {
        if(amount >= AmountException.NEG_MAX && amount <= AmountException.POS_MAX)
            return;
        
        throw new AmountException("Amount exceeds possible value");
    }
}
