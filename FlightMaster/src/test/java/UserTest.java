/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.thesoftwareguild.flightmaster.daos.UserDao;
import com.thesoftwareguild.flightmaster.models.User;
import org.junit.After;
import org.junit.Assert;
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
public class UserTest {
    
    private ApplicationContext ctx;
    private JdbcTemplate jdbcTemplate;
    private UserDao userDao;
    private User testUser1;
    
    public UserTest() {
    }
    
    @Before
    public void setUp() {
        
        ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        jdbcTemplate = ctx.getBean("jdbcTemplate", JdbcTemplate.class);
        userDao = ctx.getBean("userDao", UserDao.class);
        
        
        
        testUser1 = new User();
        testUser1.setFirstName("Bob");
        testUser1.setLastName("Dole");
        testUser1.setUsername("bdole");
        testUser1.setEmail("bob@dole.com");
        testUser1.setPassword("president");
        testUser1.addAuthority("USER");
        
        
    }
    
    @After
    public void tearDown() {
        jdbcTemplate.execute("DELETE FROM authorities WHERE id > 0");
        jdbcTemplate.execute("DELETE FROM users WHERE id > 0");
        
    }

    //@Test
    public void addTest1(){
        User res = userDao.addUser(testUser1);
        
        Assert.assertNotEquals(0, res.getUserId());
    }
}
