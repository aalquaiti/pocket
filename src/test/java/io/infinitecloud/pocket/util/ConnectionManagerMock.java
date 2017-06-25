/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.util;

/**
 * Mock Object
 * @author Aymen Alquaiti
 * 
 */
public class ConnectionManagerMock implements ConnectionManager
{
    boolean connected;
    
    public ConnectionManagerMock()
    {
        connected = false;
    }

    @Override
    public Object getConnection()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void open()
    {
        if(connected)
            throw new RuntimeException("Connection already opened");
        
        connected = true;
    }

    @Override
    public void close()
    {
        if(!connected)
            throw new RuntimeException("No open connection found");
        
        connected = false;
    }

}
