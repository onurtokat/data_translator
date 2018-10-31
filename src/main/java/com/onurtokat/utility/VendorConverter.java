package com.onurtokat.utility;

import com.onurtokat.init.VendorDataException;
import com.onurtokat.init.VendorDataFileNotFoundException;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class VendorConverter {

    private static final String INITIAL_VENDORS_PATH = "initialData/vendor.txt";
    private static Map<String, String> vendors = new HashMap<>();

    public static Map<String, String> getVendors() {
        return vendors;
    }

    public static void init() {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(INITIAL_VENDORS_PATH), Charset.forName("UTF-8"))) {
            String currentLine = null;
            try {
                while ((currentLine = reader.readLine()) != null) {//while there is content on the current line
                    String[] splitted = currentLine.split("\\t");
                    vendors.put(splitted[0], splitted[1]);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new VendorDataException("One of the vendor file's fields are not compatible format", e.getCause());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new VendorDataFileNotFoundException("Vendor file does not exist in the defined path", ex.getCause());
        }
    }

    public static String getVendorName(String vendorName) {
        if (getVendors().get(vendorName) == null) {
            return "NA";
        } else {
            return getVendors().get(vendorName);
        }
    }

    public static boolean isMatchingWithVendors(String vendorName) {
        if (getVendors().containsKey(vendorName)) {
            return true;
        } else {
            return false;
        }
    }
}
