/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

/**
 * Foreign Exchange utility
 * @author Aymen Alquaiti
 * <p>Date: 22/02/2016
 */
public class FXUtil
{
    /**
     * Retrieve exchange amount by multiplying it with rate
     * @param amount Amount
     * @param decimal Decimal position of amount
     * @param rate FX rate
     * @param pos Decimal position of FX Rate
     * @return Amount after performing exchange by multiplication
     */
    public static long multiply(long amount, int decimal, long rate, int pos)
    {
        return makeFX(amount, decimal, rate, pos, true);
    }
    
    /**
     * Retrieve exchange amount by dividing it with rate
     * @param amount Amount
     * @param decimal Decimal position of amount
     * @param rate FX rate
     * @param pos Decimal position of FX Rate
     * @return Amount after performing exchange by division
     */
    public static long divide(long amount, int decimal, long rate, int pos)
    {
        return makeFX(amount, decimal, rate, pos, false);
    }
    
    /**
     * Retrieve amount in primary currency by doing foreign exchange
     * @param amount Foreign currency amount
     * @param decimal Primary currency decimal place
     * @param rate FX rate
     * @param pos Decimal position of FX Rate
     * @param reciprocal Whether rate is reciprocal. If reciprocal, this means the pair is
     * CurrentCurrency/PrimaryCurrency
     * @return 
     */
    public static long makeFX(long amount, int decimal, long rate, int pos, boolean reciprocal)
    {
        BigDecimal result = new BigDecimal(amount);        
        result = result.movePointLeft(decimal);                
        BigDecimal rte = new BigDecimal(rate);
        rte = rte.movePointLeft(pos);    
        
        if(!reciprocal)
            result = result.divide(rte, decimal, RoundingMode.HALF_EVEN);                
        else
        {
            result = result.multiply(rte);
            result =  result.setScale( decimal, RoundingMode.HALF_EVEN);
        }
        result = result.movePointRight(decimal);        
        return result.longValue();
    }
    
    /**
     * Convert Given amount in long to BigDecimal
     * @param amount Amount to convert
     * @param pos Decimal position 
     * @return BigDecimal representation long amount
     */
    public static BigDecimal makeAmount(long amount, int pos)
    {
        BigDecimal result = new BigDecimal(amount);
        result = result.movePointLeft(pos);
        
        return result;
    }
    
    /**
     * Retrieve FX Rate between buying and selling amount. Rate is calculated as
     * From Amount / To Amount
     * @param fromAmount From Amount. Also known as sell amount
     * @param fromDecimal From amount decimal position
     * @param toAmount To Amount. Also known as buy amount
     * @param toDecimal To amount decimal position
     * @return Map with two keys: "rate" for fx rate, and "pos" for decimal position
     */
    public static Map<String, Long> getRate(long fromAmount, int fromDecimal, long toAmount, int toDecimal)
    {
        Map<String, Long> result = new HashMap<>(2);
        long rate;
        int pos;
        BigDecimal fAmount = makeAmount(fromAmount, fromDecimal);        
        BigDecimal tAmount = makeAmount(toAmount, toDecimal);        
        fAmount = fAmount.divide(tAmount, MathContext.DECIMAL32);
        pos = Math.max(0, fAmount.stripTrailingZeros().scale());
        if(pos > 0)
            rate = fAmount.movePointRight(pos).longValue();
        else
            rate = fAmount.longValue();
                
        result.put("rate", rate);
        result.put("pos", (long) pos);
              
        return result;
    }
}
