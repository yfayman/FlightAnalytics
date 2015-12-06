/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.net.URLClassLoader;
import javax.servlet.ServletContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author yan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(value = {"file:src/test/resources/test-dispatcher-servlet.xml"})
public class MockMVCTests {

   @Autowired
    private WebApplicationContext wac;
    
    private MockMvc mockMvc;


    @Before
    public void setUp() {
        
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void test() throws Exception{   
        mockMvc.perform(new RequestBuilder(){

            @Override
            public MockHttpServletRequest buildRequest(ServletContext servletContext) {
                return new MockHttpServletRequest(servletContext,"GET", "/");   
            }
            
        }).andExpect(status().isOk());
    }
}
