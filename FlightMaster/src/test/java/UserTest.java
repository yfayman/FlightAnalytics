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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.MethodArgumentNotValidException;

/**
 *
 * @author yan
 */
public class UserTest {
    
    private ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
    private JdbcTemplate jdbcTemplate = ctx.getBean("jdbcTemplate", JdbcTemplate.class);
    private UserDao userDao = ctx.getBean("userDao", UserDao.class);
    private User testUser1;
    
    public UserTest() {
    }
    
    @Before
    public void setUp() {   
        
        
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

    @Test
    public void addTest1(){
        User res = userDao.addUser(testUser1);
        
        Assert.assertNotEquals(0, res.getUserId());
    }
    
    @Test(expected = DataIntegrityViolationException.class)
    public void addTest2(){
        
        User testUser2 = new User();
        userDao.addUser(testUser2);
        
        // should never reach this point 
        Assert.assertTrue(false);
        
    }
    
    @Test
    public void getTest1(){
        User user = userDao.getByUsername("bdole");
        
        Assert.assertNull(user.getUserId());
        Assert.assertEquals("Bob", user.getFirstName());
        Assert.assertEquals("Dole", user.getLastName());
        Assert.assertEquals("bob@dole.com", user.getEmail());
        Assert.assertEquals("president", user.getPassword());
        Assert.assertEquals(1, user.getRoles().size());
    }
}
