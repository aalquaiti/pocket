/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package me.infinitecloud.pocket.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Transaction Entity
 * @author Aymen Alquaiti
 * Date: 30/01/2016
 */
public class Transaction
{
    private long id;
    private Date date;
    private String comment;
    private List<Entry> entries;
    
    public Transaction()
    {
        entries = new ArrayList<>();
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
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
