/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.thesoftwareguild.flightmaster.daos.RequestDao;
import com.thesoftwareguild.flightmaster.daos.UserDao;
import com.thesoftwareguild.flightmaster.models.RequestParameters;
import com.thesoftwareguild.flightmaster.models.User;
import java.util.Date;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author yan
 */
public class RequestTest {

    private ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
    private JdbcTemplate jdbcTemplate  = ctx.getBean("jdbcTemplate", JdbcTemplate.class);
    private RequestDao requestDao = ctx.getBean("requestDao", RequestDao.class);
    private UserDao userDao = ctx.getBean("userDao", UserDao.class);
    RequestParameters request;

    public RequestTest() {
    }

    @Before
    public void setUp() {


        User testUser1 = new User();
        testUser1.setFirstName("Bob");
        testUser1.setLastName("Dole");
        testUser1.setUsername("bdole");
        testUser1.setEmail("bob@dole.com");
        testUser1.setPassword("president");
        testUser1.addAuthority("USER");
        userDao.addUser(testUser1);
        
        request = new RequestParameters();
        request.setAdultPassengers(1);
        request.setDepDate(new Date(115,11,25));
        request.setDestination("LGA");
        request.setInterval(120);
        request.setMaxStops(1);
        request.setNumberQueries(20);
        request.setOrigin("ORD");
        request.setRetDate(new Date(115,11,30));
        
        int id = userDao.getByUsername("bdole").getUserId();
        request.setUserId(id);
    }

    @After
    public void tearDown() {
        jdbcTemplate.execute("DELETE FROM flightdata WHERE ID > 0");
        jdbcTemplate.execute("DELETE FROM requestdata WHERE ID > 0");
        jdbcTemplate.execute("DELETE FROM requests WHERE id > 0");
        jdbcTemplate.execute("DELETE FROM authorities WHERE id > 0");
        jdbcTemplate.execute("DELETE FROM users WHERE id > 0");
    }

//    private static final String SQL_ADD_REQUEST = "INSERT INTO requests (user_id, origin, destination, depart_date, return_date, adult_passengers, child_passengers, senior_passengers, max_stops, interval , queries_left) "
//            + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    @Test
    public void addTest1() {
              
        RequestParameters ret = requestDao.add(request);
        Assert.assertEquals(1, ret.getAdultPassengers());
        Assert.assertEquals(0, ret.getChildPassengers());
        Assert.assertEquals(1, ret.getMaxStops());
        Assert.assertEquals(120, ret.getInterval());
        Assert.assertEquals(new Date(115,11,25), ret.getDepDate());
        Assert.assertEquals(new Date(115,11,30), ret.getRetDate());
        Assert.assertEquals("ORD", ret.getOrigin());
        Assert.assertEquals("LGA", ret.getDestination());

    }
    
    @Test
    public void getTest1(){
        RequestParameters added = requestDao.add(request);
        RequestParameters result = requestDao.getRequestByRequestId(added.getRequestId());
        Assert.assertEquals(1, result.getAdultPassengers());
        Assert.assertEquals(0, result.getChildPassengers());
        Assert.assertEquals(1, result.getMaxStops());
        Assert.assertEquals(120, result.getInterval());
        Assert.assertEquals(new Date(115,11,25), result.getDepDate());
        Assert.assertEquals(new Date(115,11,30), result.getRetDate());
        Assert.assertEquals("ORD", result.getOrigin());
        Assert.assertEquals("LGA", result.getDestination());
    }
    
    @Test(expected = EmptyResultDataAccessException.class)
    public void deleteTest1(){
        int id = requestDao.add(request).getRequestId();
        requestDao.delete(id);
        requestDao.getRequestByRequestId(id);
    }
}
