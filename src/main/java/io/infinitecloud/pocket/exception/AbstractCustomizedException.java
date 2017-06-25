/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.exception;

/**
 * An Abstract Exception with different error states
 * @author Aymen Alquaiti
 * Date: 31/01/2016
 */
public abstract class AbstractCustomizedException extends RuntimeException
{    
    private static final long serialVersionUID = -2886760319113374990L;

    private int state;
    
    /**
     * Initialize empty exception
     * @param state Exception State
     */
    public AbstractCustomizedException(int state)
    {
        super();
        this.state = state;
    }
    
    /**
     * Initialize exception with specified method
     * @param state Exception State
     * @param message Message to provide
     */
    public AbstractCustomizedException(int state, String message)
    {
        super(message);
        this.state = state;
    }
    
    /**
     * Initialize exception with specified method and throwable object
     * @param state Exception State
     * @param message Message to provide
     * @param cause Throwable object that indicate the cause
     */
    public AbstractCustomizedException(int state, String message, Throwable cause)
    {
        super(message, cause);
        this.state = state;
    }
    
    /**
     * Initialize exception with specified throwable object
     * @param state Exception State
     * @param cause Throwable object that indicate the cause
     */
    public AbstractCustomizedException(int state, Throwable cause)
    {
        super(cause);
        this.state = state;
    }
    
    /**
     * Initialize exception with further control
     * @param state Exception State
     * @param message Message to provide
     * @param cause Throwable object that indicate the cause
     * @param enableSuppression whether or not suppression is enabled or disabled
     * @param writableStackTrace whether or not the stack trace should be writable
     */
    public AbstractCustomizedException(int state,String message, Throwable cause, 
                                boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
        this.state = state;
    }
    
    /**
     * Set Exception state
     * @param state Exception State
     */
    public void setState(int state)
    {
        this.state = state;        
    }
    
    /**
     * Retrieve Exception State
     * @return integer of Exception State
     */
    public int getState()
    {
        return state;
    }
}
