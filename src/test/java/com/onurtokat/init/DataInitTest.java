package com.onurtokat.init;

import com.onurtokat.domain.DataDomain;
import com.onurtokat.repositories.DataRepository;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author onurtokat
 */
public class DataInitTest {

    private final DataRepository dataRepositoryMock = mock(DataRepository.class);
    private final DataDomain dataTest = new DataDomain();
    private final List<DataDomain> dataListTest = new ArrayList<>();
    private ClassLoader classLoader;

    @Before
    public void init() {
        List<String> list = new ArrayList<>();
        dataTest.setId("ID2");
        list.add("VAL21");
        list.add("VAL22");
        list.add("VAL23");
        dataTest.setDetails(list);
        dataListTest.add(dataTest);

        when(dataRepositoryMock.findAll()).thenReturn(dataListTest);
        DataInit DataInitTest = new DataInit(dataRepositoryMock);
        classLoader = DataInitTest.getClass().getClassLoader();
    }

    @Test
    public void run() {
        assertTrue(dataRepositoryMock.count() == 0);
    }

    @Test(expected = DataException.class)
    public void DataExceptionTest() {
        DataInit.fileLoader(new File(classLoader.getResource("data_dup_column.txt").getFile()).getAbsolutePath());
    }

    @Test(expected = DataFileNotFoundException.class)
    public void DataFileNotFoundExceptionTest() {
        DataInit.fileLoader("src/test/resources/data.txt");
    }
}