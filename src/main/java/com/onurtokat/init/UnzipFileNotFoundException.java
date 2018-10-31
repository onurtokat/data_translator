package com.onurtokat.init;

/** UnzipFileNotFoundException is used when file is not found
 *
 * @author onurtokat
 */
public class UnzipFileNotFoundException extends UnzipException{

    private static final long serialVersionUID = 1L;

    /**
     * UnzipFileNotFoundException method gives message
     * when any FileNotFoundException occur
     *
     * @param message Exception message to be clear about exception
     */
    public UnzipFileNotFoundException(String message) {
        super(message);
    }

    /**
     * UnzipFileNotFoundException method gives message when any RuntimeExceptions
     * occur
     *
     * @param message Exception message to be clear about exception
     * @param cause   default java API exception stack trace stream
     */
    public UnzipFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
