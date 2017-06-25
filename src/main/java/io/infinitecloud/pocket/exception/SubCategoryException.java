/*
 * Copy Righted Material.
 * All Rights reserved to Aymen Alquaiti.
 */

package io.infinitecloud.pocket.exception;

/**
 * Exceptions related to Category
 * @author Aymen Alquaiti
 * Date: 31/01/2016
 */
public class SubCategoryException extends AbstractCustomizedException
{   
    private static final long serialVersionUID = -2187096346166092207L;
    
    /**
     * Exception state indicating sub category does not exists
     */
    public static final int NOT_EXISTS = 1;        
    
    /**
     * Exception state indicating sub category is not linked to certain category
     */
    public static final int NOT_LINKED = 2;
    
    /**
     * Exception state indicated that sub category amount or sum of an array of sub category
     * amount does not equal to category amount
     */
    public static final int NOT_EQUAL = 3;

    /**
     * Initialize empty exception
     * @param state Exception State
     */
    public SubCategoryException(int state)
    {
        super(state);
    }

    /**
     * Initialize exception with specified method
     * @param state Exception State
     * @param message Message to provide
     */
    public SubCategoryException(int state, String message)
    {
        super(state, message);
    }

    /**
     * Initialize exception with specified method and throwable object
     * @param state Exception State
     * @param message Message to provide
     * @param cause Throwable object that indicate the cause
     */
    public SubCategoryException(int state, String message, Throwable cause)
    {
        super(state, message, cause);
    }

    /**
     * Initialize exception with specified throwable object
     * @param state Exception State
     * @param cause Throwable object that indicate the cause
     */
    public SubCategoryException(int state, Throwable cause)
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
    public SubCategoryException(int state, String message, Throwable cause, 
            boolean enableSuppression, boolean writableStackTrace)
    {
        super(state, message, cause, enableSuppression, writableStackTrace);
    }    
}
