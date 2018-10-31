package com.onurtokat.utility;

import com.onurtokat.init.HeaderDataException;
import com.onurtokat.init.HeaderDataFileNotFoundException;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/** HeaderConverter utility class provides header fields operations
 * which are used for showing in HTML table
 *
 * @author onurtokat
 */
public class HeaderConverter {

    private static final String INITIAL_HEADERS_PATH = "initialData/header.txt";
    private static Map<String, String> headers = new HashMap<>();

    //Getter
    public static Map<String, String> getHeaders() {
        return headers;
    }

    /** init method collects header key-value pairs for header configuration
     * file
     */
    public static void init() {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(INITIAL_HEADERS_PATH), Charset.forName("UTF-8"))) {
            String currentLine = null;
            try {
                while ((currentLine = reader.readLine()) != null) {//while there is content on the current line
                    String[] splitted = currentLine.split("\\t");
                    headers.put(splitted[0], splitted[1]);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new HeaderDataException("One of the header.txt file's fields are not compatible format", e.getCause());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new HeaderDataFileNotFoundException("Header file does not exist in the defined path", ex.getCause());
        }
    }

    /** getHeaderName method returns value if key exists on the main
     * hashmap
     *
     * @param headerName key for searching in hash map
     * @return if key exists then value is returned
     */
    public static String getHeaderName(String headerName) {
        if (getHeaders().get(headerName) == null) {
            return "NA";
        } else {
            return getHeaders().get(headerName);
        }
    }

    /** isMatchingWithHeaders is used for key searching on header
     * key-value pairs
     *
     * @param headerName key for searching in hash map
     * @return if key exists then boolean response is returned
     */
    public static boolean isMatchingWithHeaders(String headerName) {
        if (getHeaders().containsKey(headerName)) {
            return true;
        } else {
            return false;
        }
    }
}
