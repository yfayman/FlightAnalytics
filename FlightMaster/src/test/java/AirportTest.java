/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.thesoftwareguild.flightmaster.daos.AirportDataDao;
import com.thesoftwareguild.flightmaster.models.AirportData;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author yan
 */
public class AirportTest {
    
    private ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
    private JdbcTemplate jdbcTemplate  = ctx.getBean("jdbcTemplate", JdbcTemplate.class);
    private AirportDataDao airportDataDao = ctx.getBean("airportDataDao", AirportDataDao.class);
    
    public AirportTest() {
    }
    
    @Before
    public void setUp() {
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
    public void test(){
        
        AirportData allAirports = airportDataDao.getAllAirports();
        
        Assert.assertNotNull(allAirports);
        Assert.assertNotNull(allAirports.getIataCodes());
        Assert.assertNotEquals(0, allAirports.getIataCodes().size());
        
    }
}
