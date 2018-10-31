package com.onurtokat.init;

/**DataFileNotFoundException is used when file is not found
 *
 * @author onurtokat
 */
public class DataFileNotFoundException extends DataException{

    private static final long serialVersionUID = 1L;

    /**
     * DataFileNotFoundException method gives message
     * when any FileNotFoundException occur
     *
     * @param message Exception message to be clear about exception
     */
    public DataFileNotFoundException(String message) {
        super(message);
    }

    /**
     * DataFileNotFoundException method gives message when any RuntimeExceptions
     * occur
     *
     * @param message Exception message to be clear about exception
     * @param cause   default java API exception stack trace stream
     */
    public DataFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
