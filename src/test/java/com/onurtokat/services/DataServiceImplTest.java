package com.onurtokat.services;

import com.onurtokat.domain.DataDomain;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author onurtokat
 */
public class DataServiceImplTest {

    private final DataService dataServiceMock = mock(DataService.class);
    private final DataDomain data = new DataDomain();
    private final List<DataDomain> dataListTest = new ArrayList<>();

    @Before
    public void init() {
        List<String> list = new ArrayList<>();
        data.setId("ID1");
        list.add("VAL11");
        list.add("VAL12");
        list.add("VAL12");
        data.setDetails(list);
        dataListTest.add(data);
    }

    @Test
    public void listAll() {
        when(dataServiceMock.listAll()).thenReturn(dataListTest);
        assertNotNull(dataServiceMock.listAll());
        assertEquals(dataServiceMock.listAll(), dataListTest);
    }
}