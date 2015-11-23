/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.thesoftwareguild.flightmaster.models.Flight;
import com.thesoftwareguild.flightmaster.models.RequestParameters;
import com.thesoftwareguild.flightmaster.queryExecutor.Request;
import com.thesoftwareguild.flightmaster.queryProcessor.FlightQuery;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author yan
 */
public class FlightQueryTest {
    
    private ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
    private RequestParameters requestParameters;
    
    public FlightQueryTest() {
    }
    
    @Before
    public void setUp() {
        requestParameters = new RequestParameters();
        requestParameters.setAdultPassengers(1);
        requestParameters.setDepDate(new Date(115,11,25));
        requestParameters.setDestination("LGA");
        requestParameters.setInterval(120);
        requestParameters.setMaxStops(1);
        requestParameters.setNumberQueries(20);
        requestParameters.setOrigin("ORD");
        requestParameters.setRetDate(new Date(115,11,30));
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}eq
    
    @Test
    public void flightQueryInjectionTest(){
        
        try {
            Request req = ctx.getBean("request", Request.class);
            FlightQuery result = req.getQuery();
            req.setRequestParameters(requestParameters);
            List<Flight> execute = req.execute();
            Assert.assertNotSame(0, execute.size());
            for (Flight flight : execute) {
                Assert.assertEquals(requestParameters.getOrigin(), flight.getOrigin());
                Assert.assertEquals(requestParameters.getDestination(), flight.getDestination());
                Assert.assertEquals(requestParameters.getRequestId(), flight.getRequestId());
            }
        } catch (IOException ex) {
            Logger.getLogger(FlightQueryTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     }
    
    
    // This test ensures that the QPX Flight Query is loaded with no errors
    @Test
    public void qpxFlightQueryLoad(){
        FlightQuery fq = ctx.getBean("qpxFlightQuery", FlightQuery.class);
       
    }
}
