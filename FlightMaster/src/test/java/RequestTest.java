/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.thesoftwareguild.flightmaster.daos.RequestDao;
import com.thesoftwareguild.flightmaster.daos.UserDao;
import com.thesoftwareguild.flightmaster.models.RequestParameters;
import com.thesoftwareguild.flightmaster.models.User;
import com.thesoftwareguild.flightmaster.queryExecutor.Request;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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

    public RequestTest() {
    }

    @Before
    public void setUp() {

//        ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
//        jdbcTemplate = ctx.getBean("jdbcTemplate", JdbcTemplate.class);
//        requestDao = ctx.getBean("requestDao", RequestDao.class);
//        userDao = ctx.getBean("userDao", UserDao.class);
        User testUser1 = new User();
        testUser1.setFirstName("Bob");
        testUser1.setLastName("Dole");
        testUser1.setUsername("bdole");
        testUser1.setEmail("bob@dole.com");
        testUser1.setPassword("president");
        testUser1.addAuthority("USER");
    }

    @After
    public void tearDown() {
        jdbcTemplate.execute("DELETE FROM requests WHERE id > 0");
        jdbcTemplate.execute("DELETE FROM authorities WHERE id > 0");
        jdbcTemplate.execute("DELETE FROM users WHERE id > 0");
    }

    @Test
    public void addTest1() {

        RequestParameters request = new RequestParameters();

    }
}
