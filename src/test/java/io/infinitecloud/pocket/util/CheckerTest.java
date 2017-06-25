/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.util;

import io.infinitecloud.pocket.exception.AmountException;
import org.junit.Test;

/**
 * Unit Test
 * @author Aymen Alquaiti
 * <p>Date: 10/11/2016</p>
 */
public class CheckerTest
{
    @Test
    public void amountWithinRange()
    {
        Checker.withinRange(-39394858585L);
        Checker.withinRange(39394858585L);
    }
    
    @Test(expected = AmountException.class)
    public void amountOutOfRange()
    {
        Checker.withinRange(Long.MAX_VALUE);
    }
}
