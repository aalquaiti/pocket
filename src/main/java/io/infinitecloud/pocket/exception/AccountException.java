/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.exception;

/**
 * Exceptions related to Normal Account
 * @author Aymen Alquaiti
 * Date: 31/01/2016
 */
public class AccountException extends AbstractCustomizedException
{
    private static final long serialVersionUID = 6828221380165708760L;
    
    
    /**
     * Exception state indicating Account does not exists
     */
    public static final int NOT_EXISTS = 1;    
    
    /**
     * Exception state indicating Account is closed
     */
    public static final int IS_CLOSED = 2;

    /**
     * Initialize empty exception
     * @param state Exception State
     */
    public AccountException(int state)
    {
        super(state);
    }

    /**
     * Initialize exception with specified method
     * @param state Exception State
     * @param message Message to provide
     */
    public AccountException(int state, String message)
    {
        super(state, message);
    }

    /**
     * Initialize exception with specified method and throwable object
     * @param state Exception State
     * @param message Message to provide
     * @param cause Throwable object that indicate the cause
     */
    public AccountException(int state, String message, Throwable cause)
    {
        super(state, message, cause);
    }

    /**
     * Initialize exception with specified throwable object
     * @param state Exception State
     * @param cause Throwable object that indicate the cause
     */
    public AccountException(int state, Throwable cause)
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
    public AccountException(int state, String message, Throwable cause, 
            boolean enableSuppression, boolean writableStackTrace)
    {
        super(state, message, cause, enableSuppression, writableStackTrace);
    }    
}
