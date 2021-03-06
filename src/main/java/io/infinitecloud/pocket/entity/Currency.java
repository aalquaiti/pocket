/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.entity;

/**
 * Currency Entity
 * @author Aymen Alquaiti
 * <p>Date: 30/01/2016</p>
 */
public class Currency
{
    private long id;
    private String name;
    private long rate;
    private boolean reciprocal;
    private int decimal;
    private int pos;
    private boolean used;
    private String symbol;    
    private String abbreviation;
    private boolean hasAbreviation;
    
    public Currency()
    {
        
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public long getRate()
    {
        return rate;
    }

    public void setRate(long rate)
    {
        this.rate = rate;
    }

    public boolean isReciprocal()
    {
        return reciprocal;
    }

    /** Set if the rate of this currency indicates it as reciprocal against
     * primary currency. For example, if this currency is YER and base currency is USD,
     * if this currency is set as reciprocal, then the real rate is 1 / rate.
     * E.g: Rate is set as 10050. Calculated Real Rate (See {@link #setDecimal(int) }) 
     * is 100.50, So final rate will be 1 / 100.50
     * @param reciprocal Whether current currency is reciprocal or not
     */
    public void setReciprocal(boolean reciprocal)
    {
        this.reciprocal = reciprocal;
    }

    public int getDecimal()
    {
        return decimal;
    }
    
    /**
     * Sets how many decimal places a current can have. E.g: USD has a currency
     * with two decimal places.
     * @param decimal 
     */
    public void setDecimal(int decimal)
    {
        this.decimal = decimal;
    }

    public boolean isUsed()
    {
        return used;
    }

    /**
     * Indicate that current currency is used within the system. Helps to load
     * only used currencies for memory optimization
     * @param used Whether used or not 
     */
    public void setUsed(boolean used)
    {
        this.used = used;
    }

    public int getPos()
    {
        return pos;
    }

    /**
     * Set decimal position within given Rate. For example: if rate was 100.50,
     * position will be 2. Position is the power (^)  of 10, which is used to indicate
     * the position of the decimal. If rate is stored as 10050, and position is 2, then
     * the position will be calculated as follows:
     * <p>Divider = 10 ^ (position)</p>
     * <p>Divider = 10 ^ 2</p>
     * <p>Divider = 100 </p>
     * <p>Rate = Rate / Divider </p>
     * <p>Rate = 10050 / 100 </p>
     * <p>Rate = 100.50 </p>
     * 
     * @param pos Decimal position
     */
    public void setPos(int pos)
    {
        // TODO - Check if setting a negative position will give an accurate result        
        this.pos = pos;
    }

    public String getSymbol()
    {
        return symbol;
    }

    public void setSymbol(String symbol)
    {
        this.symbol = symbol;
    }

    public String getAbbreviation()
    {
        return abbreviation;
    }

    public void setAbbreviation(String abreviation)
    {
        this.abbreviation = abreviation;
    }

    public boolean hasAbbreviation()
    {
        return hasAbreviation;
    }

    public void setHasAbbreviation(boolean hasAbreviation)
    {
        this.hasAbreviation = hasAbreviation;
    }
}
