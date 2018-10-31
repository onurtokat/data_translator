package com.onurtokat.init;

/** VendorDataException used for Vendor file operation exceptions
 *
 * @author onurtokat
 */
public class VendorDataException extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /** VendorDataException is used with only message parameter
     *
     * @param message message for meaningful exception
     */
    public VendorDataException(String message) {
        super(message);
    }

    /** Overloaded VendorDataException method accepts message parameter
     * and default Exception message
     *
     * @param message message for meaningful exception
     * @param cause default Exception message
     */
    public VendorDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
