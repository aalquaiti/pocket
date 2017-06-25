/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.entity;

import java.sql.Date;
import java.util.Map;

/**
 * Configuration and cashing for the pocket system
 * @author Aymen Alquaiti
 * <p>Date: 15/02/2016</p>
 */
public class PocketConfig
{
    private long version;
    private long primaryCurrency;
    private long defaultCategory;
    private Date currentDate;
    private Map<Long, Currency> currency;

    public PocketConfig()
    {
        
    }

    public long getVersion()
    {
        return version;
    }

    public void setVersion(long version)
    {
        this.version = version;
    }    

    public long getPrimaryCurrency()
    {
        return primaryCurrency;
    }

    public void setPrimaryCurrency(long primaryCurrency)
    {
        this.primaryCurrency = primaryCurrency;
    }

    public long getDefaultCategory()
    {
        return defaultCategory;
    }

    public void setDefaultCategory(long defaultCategory)
    {
        this.defaultCategory = defaultCategory;
    }

    public Date getCurrentDate()
    {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate)
    {
        this.currentDate = currentDate;
    }

    public Map<Long, Currency> Currency()
    {
        return currency;
    }

    public void setCurrency(Map<Long, Currency> currency)
    {
        this.currency = currency;
    }    
}
