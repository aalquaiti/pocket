/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.exception;

/**
 * Exception related to maximum amount allowed
 * @author Aymen Alquaiti
 * <p>Date: 10/11/2016</p>
 */
public class AmountException extends RuntimeException
{    
    private static final long serialVersionUID = 3252309066460819765L;
    
    /**
     * Maximum positive amount allowed
     */
    public static final long POS_MAX = 999999999999999L;
    
    /**
     * Maximum negative amount allowed
     */
    public static final long NEG_MAX = -999999999999999L;

    /**
     * Initialize empty exception     
     */
    public AmountException()
    {
        super();        
    }
    
    /**
     * Initialize exception with specified method
     
     * @param message Message to provide
     */
    public AmountException(String message)
    {
        super(message);        
    }
    
    /**
     * Initialize exception with specified method and throwable objec
     
     * @param message Message to provide
     * @param cause Throwable object that indicate the cause
     */
    public AmountException(String message, Throwable cause)
    {
        super(message, cause);        
    }
    
    /**
     * Initialize exception with specified throwable object     
     * @param cause Throwable object that indicate the cause
     */
    public AmountException(Throwable cause)
    {
        super(cause);        
    }
    
    /**
     * Initialize exception with further control     
     * @param message Message to provide
     * @param cause Throwable object that indicate the cause
     * @param enableSuppression whether or not suppression is enabled or disabled
     * @param writableStackTrace whether or not the stack trace should be writable
     */
    public AmountException(String message, Throwable cause, 
                                boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);        
    }
}
