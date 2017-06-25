/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.util;

import java.sql.Date;
import java.util.Calendar;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test Unit
 * @author Aymen Alquaiti
 * <p>Date: 17/08/2016</p>
 */
public class DateTimeUtilTest
{
    private Calendar cal;
    private Date expected;
    
    @Before
    public void setUp()
    {
        cal = Calendar.getInstance();
        cal.set(2033, Calendar.MARCH, 31);
        expected = new Date(cal.getTimeInMillis());
    }
    
    @Test
    public void asLastDay()
    {
        cal.set(2033, Calendar.MARCH, 5);
        Date actual = new Date(cal.getTimeInMillis());
        actual = DateTimeUtil.asLastDay(actual);
        Assert.assertEquals(expected, actual);
    }
}
