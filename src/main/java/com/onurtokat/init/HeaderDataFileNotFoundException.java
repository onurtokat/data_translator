package com.onurtokat.init;

/** HeaderDataFileNotFoundException is used when file is not found
 *
 * @author onurtokat
 */
public class HeaderDataFileNotFoundException extends HeaderDataException{

    private static final long serialVersionUID = 1L;

    /**
     * HeaderDataFileNotFoundException method gives message
     * when any FileNotFoundException occur
     *
     * @param message Exception message to be clear about exception
     */
    public HeaderDataFileNotFoundException(String message) {
        super(message);
    }

    /**
     * HeaderDataFileNotFoundException method gives message when any RuntimeExceptions
     * occur
     *
     * @param message Exception message to be clear about exception
     * @param cause   default java API exception stack trace stream
     */
    public HeaderDataFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
