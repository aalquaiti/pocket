/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Transaction Entity
 * @author Aymen Alquaiti
 * <p>Date: 30/01/2016</p>
 */
public class Transaction
{
    private long id;
    private Date date;
    private String comment;
    private Payee payee;
    private TransactionType type;
    private List<Entry> entries;    
    private boolean fx;
    private int rate;
    private int pos;
    private boolean reciprocal;
    
    public Transaction()
    {
        entries = new ArrayList<>();
        fx = false;
        rate = 1;
        pos = 0;
        reciprocal = false;
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

    public Payee getPayee()
    {
        return payee;
    }

    public void setPayee(Payee payee)
    {
        this.payee = payee;
    }

    public TransactionType getType()
    {
        return type;
    }

    public void setType(TransactionType type)
    {
        this.type = type;
    }

    public List<Entry> getEntries()
    {
        return entries;
    }

    public void setEntries(List<Entry> entries)
    {
        this.entries = entries;
    }

    public boolean isFx()
    {
        return fx;
    }

    public void setFx(boolean fx)
    {
        this.fx = fx;
        
        if(!fx)
        {
            rate = 1;
            pos = 0;
            reciprocal = false;
        }
    }

    public int getRate()
    {
        return rate;
    }

    public void setRate(int rate)
    {
        this.rate = rate;
    }

    public int getPos()
    {
        return pos;
    }

    public void setPos(int pos)
    {
        this.pos = pos;
    }

    public boolean isReciprocal()
    {
        return reciprocal;
    }

    public void setReciprocal(boolean reciprocal)
    {
        this.reciprocal = reciprocal;
    }
}
