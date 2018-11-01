package com.onurtokat.utility;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VendorConverterTest {

    private final static String TEST_VENDOR_KEY = "ID2";

    @Before
    public void initial() {
        VendorConverter.init();
    }

    @Test
    public void init() {
        assertNotNull(VendorConverter.getVendors());
        assertTrue(VendorConverter.getVendors().size() > 0);
    }

    @Test
    public void getVendorName() {
        if (VendorConverter.getVendors().containsKey(TEST_VENDOR_KEY)) {
            assertNotNull(VendorConverter.getVendorName(TEST_VENDOR_KEY));
        } else {
            assertEquals("NA", VendorConverter.getVendorName(TEST_VENDOR_KEY));
        }
    }

    @Test
    public void isMatchingWithVendors() {
        if (VendorConverter.getVendors().containsKey(TEST_VENDOR_KEY)) {
            assertTrue(VendorConverter.isMatchingWithVendors(TEST_VENDOR_KEY));
        } else {
            assertFalse(VendorConverter.isMatchingWithVendors(TEST_VENDOR_KEY));
        }
    }
}