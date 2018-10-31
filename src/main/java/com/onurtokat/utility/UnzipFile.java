package com.onurtokat.utility;

import com.onurtokat.init.UnzipException;
import com.onurtokat.init.UnzipFileNotFoundException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/** FileType enum defines files types as standart
 */
enum FileType {
    DATA("data.txt"), HEADER("header.txt"), VENDOR("vendor.txt");

    private String type;

    FileType(String type) {
        this.type = type;
    }

    public String type() {
        return type;
    }
}

/** UnzipFile extracts the upload file. This file should contains
 * three type files. Main data, header configuration file and vendor
 * configuration file are they.
 *
 * Namings of the files are standardized. Therefore, if the file which is
 * named with wrong, then operation is broke
 *
 * @author onurtokat
 */
public class UnzipFile {

    private static final String UNZIP_PATH = "initialData";

    /** unzipFiles method extracts zipped file where path which is given as
     * parameter
     *
     * @param path zipped file path
     */
    public static void unzipFiles(String path) {

        File destDir = new File(UNZIP_PATH);
        byte[] buffer = new byte[1024];
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(path))) {
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {

                if (!zipEntry.getName().equals(FileType.DATA.type()) && !zipEntry.getName().equals(FileType.HEADER.type()) &&
                        !zipEntry.getName().equals(FileType.VENDOR.type())) {
                    throw new UnzipException("File names in the zip file does not named as expected: " + zipEntry.getName() +
                            " Expected: " + Arrays.toString(FileType.values()));
                }

                File newFile = newFile(destDir, zipEntry);
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                zipEntry = zis.getNextEntry();
            }
        } catch (IOException ex) {
            throw new UnzipFileNotFoundException("Unzip file does not found in the path: " + path, ex.getCause());
        }
    }

    /** newFile method creates partial files which exist on zipped file
     * on defined paths
     *
     * @param destinationDir directory which will be used for copying file
     * @param zipEntry zipped file content
     * @return partial file which exists on zipped file
     * @throws IOException IOException
     */
    public static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new UnzipException("Entry is outside of the target dir: " + zipEntry.getName());
        }
        return destFile;
    }
}
