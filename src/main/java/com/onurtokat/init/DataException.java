package com.onurtokat.init;

/**DataException used for Data file operation exceptions
 *
 * @author onurtokat
 */
public class DataException extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**DataException is used with only message parameter
     *
     * @param message message for meaningful exception
     */
    public DataException(String message) {
        super(message);
    }

    /**Overloaded DataException method accepts message parameter
     * and default Exception message
     *
     * @param message message for meaningful exception
     * @param cause default Exception message
     */
    public DataException(String message, Throwable cause) {
        super(message, cause);
    }
}
