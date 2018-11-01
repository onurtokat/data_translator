package com.onurtokat.utility;

import com.onurtokat.init.UnzipException;
import com.onurtokat.init.UnzipFileNotFoundException;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * @author onurtokat
 */
public class UnzipFileTest {

    private static final String ERROR_FILE_PATH = "src/test/resources/error_test.zip";
    private static final String ERROR_PATH = "error_test.zip";
    private static final String CORRECT_FILE_PATH = "src/test/resources/datafiles.zip";

    @Test
    public void unzipFiles() {
        UnzipFile.unzipFiles(CORRECT_FILE_PATH);
        assertTrue(new File("src/test/resources/data_dup_column.txt").exists());
        assertTrue(new File("src/test/resources/header.txt").exists());
        assertTrue(new File("src/test/resources/vendor.txt").exists());
    }

    @Test(expected = UnzipException.class)
    public void unzipFilesException() {
        UnzipFile.unzipFiles(ERROR_FILE_PATH);
    }

    @Test(expected = UnzipFileNotFoundException.class)
    public void unzipFileNotFoundException() {
        UnzipFile.unzipFiles(ERROR_PATH);
    }
}