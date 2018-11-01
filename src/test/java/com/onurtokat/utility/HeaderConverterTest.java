package com.onurtokat.utility;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class HeaderConverterTest {

    private final static String TEST_HEADER_KEY = "COL0";

    @Before
    public void initial() {
        HeaderConverter.init();
    }

    @Test
    public void init() {
        assertNotNull(HeaderConverter.getHeaders());
        assertTrue(HeaderConverter.getHeaders().size() > 0);
    }

    @Test
    public void getHeaderName() {
        if (HeaderConverter.getHeaders().containsKey(TEST_HEADER_KEY)) {
            assertNotNull(HeaderConverter.getHeaderName(TEST_HEADER_KEY));
        } else {
            assertEquals("NA", HeaderConverter.getHeaderName(TEST_HEADER_KEY));
        }
    }

    @Test
    public void isMatchingWithHeaders() {
        if (HeaderConverter.getHeaders().containsKey(TEST_HEADER_KEY)) {
            assertTrue(HeaderConverter.isMatchingWithHeaders(TEST_HEADER_KEY));
        } else {
            assertFalse(HeaderConverter.isMatchingWithHeaders(TEST_HEADER_KEY));
        }
    }
}