package com.onurtokat.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onurtokat.domain.DataDomain;
import com.onurtokat.services.DataService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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

    @Test
    public void canRetrieveDataWhenExists() throws Exception {

        //initialize data domain which will be used when checking
        DataDomain data = new DataDomain();
        List<String> list = new ArrayList<>();
        List<DataDomain> dataListTest = new ArrayList<>();
        data.setId("ID2");
        list.add("VAL21");
        list.add("VAL22");
        list.add("VAL23");
        data.setDetails(list);
        dataListTest.add(data);

        given(dataService.listAll()).willReturn(dataListTest);

        // when
        MockHttpServletResponse response = mvc.perform(
                get("/data")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).contains(
                "table"
        );
    }
}
