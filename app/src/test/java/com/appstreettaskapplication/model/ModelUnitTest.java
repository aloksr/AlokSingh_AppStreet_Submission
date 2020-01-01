package com.appstreettaskapplication.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class ModelUnitTest {

    @Test
    public void testName(){
        ListResponseModel listResponseModel = new ListResponseModel();
        listResponseModel.setName("test");
        assertEquals(listResponseModel.getName(), "test");
    }

    @Test
    public void testUsernameName(){
        ListResponseModel listResponseModel = new ListResponseModel();
        listResponseModel.setUsername("test");
        assertEquals(listResponseModel.getUsername(), "test");
    }

    @Test
    public void testType(){
        ListResponseModel listResponseModel = new ListResponseModel();
        listResponseModel.setType("test");
        assertEquals(listResponseModel.getType(), "test");
    }

    @Test
    public void testUrl(){
        ListResponseModel listResponseModel = new ListResponseModel();
        listResponseModel.setUrl("test");
        assertEquals(listResponseModel.getUrl(), "test");
    }
}
