package com.onurtokat.init;

/**HeaderDataException used for Header file operation exceptions
 *
 * @author onurtokat
 */
public class HeaderDataException extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /** HeaderDataException is used with only message parameter
     *
     * @param message message for meaningful exception
     */
    public HeaderDataException(String message) {
        super(message);
    }

    /** Overloaded HeaderDataException method accepts message parameter
     * and default Exception message
     *
     * @param message message for meaningful exception
     * @param cause default Exception message
     */
    public HeaderDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
