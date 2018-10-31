package com.onurtokat.init;

/** VendorDataFileNotFoundException is used when file is not found
 *
 * @author onurtokat
 */
public class VendorDataFileNotFoundException extends VendorDataException{

    private static final long serialVersionUID = 1L;

    /**
     * VendorDataFileNotFoundException method gives message
     * when any FileNotFoundException occur
     *
     * @param message Exception message to be clear about exception
     */
    public VendorDataFileNotFoundException(String message) {
        super(message);
    }

    /**
     * VendorDataFileNotFoundException method gives message when any RuntimeExceptions
     * occur
     *
     * @param message Exception message to be clear about exception
     * @param cause   default java API exception stack trace stream
     */
    public VendorDataFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
