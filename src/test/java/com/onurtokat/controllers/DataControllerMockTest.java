package com.onurtokat.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onurtokat.services.DataService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
public class DataControllerMockTest {

    private MockMvc mvc;

    @Mock
    private DataService dataService;

    @InjectMocks
    private DataController dataController;

    @Before
    public void setup() {
        // Initializes the JacksonTester
        JacksonTester.initFields(this, new ObjectMapper());
        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(dataController).build();
    }


}
