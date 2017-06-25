/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit Test
 * @author Aymen Alquaiti
 * <p>Date: 17/08/2016</p>
 */
public class FXUtilTest
{
    private long amount;
    private int decimal;
    long rate;
    int pos;
    long expected1;
    long expected2;
    
    @Before
    public void setUp()
    {
        amount = 2546913785L;
        decimal = 2;
        rate = 21525;
        pos = 2;        
        expected1 = 548223192221L;        
        expected2 = 11832352;        
    }
    
    @Test
    public void multiply()
    {
        long actual = FXUtil.multiply(amount, decimal, rate, pos);
        Assert.assertEquals(expected1, actual);
    }
    
    @Test
    public void divide()
    {
        long actual = FXUtil.divide(amount, decimal, rate, pos);
        Assert.assertEquals(expected2, actual);
    }
    
    @Test
    public void makeFX()
    {
        long actual1 = FXUtil.makeFX(amount, decimal, rate, pos, true);
        Assert.assertEquals(expected1, actual1);
        long actual2 = FXUtil.makeFX(amount, decimal, rate, pos, false);                
        Assert.assertEquals(expected2, actual2);
    }
    
    @Test
    public void makeAmount()
    {
        BigDecimal expected = new BigDecimal("23434234392.653");
        BigDecimal actual = FXUtil.makeAmount(23434234392653L, 3);
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void getRate()
    {
        Map<String, Long> expected = new HashMap<>(2);
        expected.put("rate", 21525L);
        expected.put("pos", 2L);
        
        Map<String, Long> actual = FXUtil.getRate(2546913785L, 2, 11832352, 2);
        
        Assert.assertEquals(expected, actual);
    }
}
