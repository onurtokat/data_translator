package com.onurtokat;

import com.onurtokat.controllers.DataController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author onurtokat
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SmokeTest {

    @Autowired
    private DataController dataController;

    @Test
    public void contexLoads() throws Exception {
        assertThat(dataController).isNotNull();
    }
}