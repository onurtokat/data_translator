package com.onurtokat.controllers;

import com.onurtokat.services.DataService;
import com.onurtokat.storage.FileSystemStorageService;
import com.onurtokat.storage.StorageProperties;
import com.onurtokat.storage.StorageService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DataControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private StorageProperties properties = new StorageProperties();
    private FileSystemStorageService service;
    private DataService dataService;
    private StorageService storageService;

    @Before
    public void init() {
        dataService = mock(DataService.class);
        storageService = mock(StorageService.class);

        properties.setLocation("target/files/" + Math.abs(new Random().nextLong()));
        service = new FileSystemStorageService(properties);
        service.init();
    }

    @Test
    public void setPlaceServiceTest() throws Exception {
        assertNotNull(dataService);
    }

    @Test
    public void setStorageServiceTest() throws Exception {
        assertNotNull(dataService);
    }

    @Test
    public void listUploadedFiles() {
        storageService.store(new MockMultipartFile("test", "test.txt", MediaType.TEXT_PLAIN_VALUE,
                "This is the data file".getBytes()));
        assertNotNull(storageService.loadAll());
    }

    @Test
    public void getDataAsTable() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port +
                        "/data",
                String.class)).contains("table");
    }
}