/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.util;

import java.sql.Date;
import java.util.Calendar;

/**
 * Utility class for Date/Time operations
 * @author Aymen Alquaiti
 * <p>Date: 17/02/2016</p>
 */
public class DateTimeUtil
{
    /**
     * Return given day as last day of the month. E.g: if given date is 16-April-2016,
     * returned date will be 30-April-2016
     * @param date Date to change
     * @return Changed date
     */
    public static Date asLastDay(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        
        return new Date(c.getTimeInMillis());
    }
}
