/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.exception;

/**
 * Exceptions related to Transaction
 * @author Aymen Alquaiti
 * <p>Date: 11/02/2016</p>
 */
public class TransactionException extends AbstractCustomizedException
{
    private static final long serialVersionUID = -2593608434516529502L;

    /**
     * Exception state indicating Transaction does not exists
     */
    public static final int NOT_EXISTS = 1;    
    

    /**
     * Initialize empty exception
     * @param state Exception State
     */
    public TransactionException(int state)
    {
        super(state);
    }

    /**
     * Initialize exception with specified method
     * @param state Exception State
     * @param message Message to provide
     */
    public TransactionException(int state, String message)
    {
        super(state, message);
    }

    /**
     * Initialize exception with specified method and throwable object
     * @param state Exception State
     * @param message Message to provide
     * @param cause Throwable object that indicate the cause
     */
    public TransactionException(int state, String message, Throwable cause)
    {
        super(state, message, cause);
    }

    /**
     * Initialize exception with specified throwable object
     * @param state Exception State
     * @param cause Throwable object that indicate the cause
     */
    public TransactionException(int state, Throwable cause)
    {
        super(state, cause);
    }

    /**
     * Initialize exception with further control
     * @param state Exception State
     * @param message Message to provide
     * @param cause Throwable object that indicate the cause
     * @param enableSuppression whether or not suppression is enabled or disabled
     * @param writableStackTrace whether or not the stack trace should be writable
     */
    public TransactionException(int state, String message, Throwable cause, 
            boolean enableSuppression, boolean writableStackTrace)
    {
        super(state, message, cause, enableSuppression, writableStackTrace);
    }    
}
