package com.onurtokat.init;

/** UnzipException used for zipped file operation exceptions
 *
 * @author onurtokat
 */
public class UnzipException  extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /** UnzipException is used with only message parameter
     *
     * @param message message for meaningful exception
     */
    public UnzipException(String message) {
        super(message);
    }

    /**Overloaded UnzipException method accepts message parameter
     * and default Exception message
     *
     * @param message message for meaningful exception
     * @param cause default Exception message
     */
    public UnzipException(String message, Throwable cause) {
        super(message, cause);
    }
}
