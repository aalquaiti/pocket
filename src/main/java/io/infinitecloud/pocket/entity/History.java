/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.entity;

import java.util.List;

/**
 * History Entity
 * @author Aymen Alquaiti
 * <p>Date: 01/02/2016</p>
 */
public class History
{
    private long openBalance;
    private List<Entry> entries;
    
    public History()
    {
        
    }

    public long getOpenBalance()
    {
        return openBalance;
    }

    public void setOpenBalance(long openBalance)
    {
        this.openBalance = openBalance;
    }

    public List<Entry> getEntries()
    {
        return entries;
    }

    public void setEntries(List<Entry> entries)
    {
        this.entries = entries;
    }
}