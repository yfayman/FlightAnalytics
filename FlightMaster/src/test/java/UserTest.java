/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.thesoftwareguild.flightmaster.daos.UserDao;
import com.thesoftwareguild.flightmaster.models.User;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
        testUser1.setUsername("bdole2");
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
    public void getByUserName1(){
        userDao.addUser(testUser1);
        User user = userDao.getByUsername("bdole2");
        
        Assert.assertNotNull(user.getUserId());
        Assert.assertEquals("Bob", user.getFirstName());
        Assert.assertEquals("Dole", user.getLastName());
        Assert.assertEquals("bob@dole.com", user.getEmail());
        Assert.assertEquals("president", user.getPassword());
        Assert.assertEquals(1, user.getRoles().size());
    }
    
    @Test
    public void getByUserId1(){
        User ret = userDao.addUser(testUser1);
        
        User user = userDao.getById(ret.getUserId());
        
        Assert.assertEquals(ret.getEmail(), user.getEmail());
        Assert.assertEquals(ret.getFirstName(), user.getFirstName());
        Assert.assertEquals(ret.getLastName(), user.getLastName());
        Assert.assertEquals(ret.getUsername(), user.getUsername());
        Assert.assertEquals(ret.getRoles().size(), user.getRoles().size());
        Assert.assertNotEquals(0, user.getRoles().size());
    }
    @Test
    public void getAllUsers(){
        userDao.addUser(testUser1);
        
        User testUser2 = new User();
        testUser2.setFirstName("George");
        testUser2.setLastName("Bush");
        testUser2.setUsername("gwb257");
        testUser2.setEmail("gwb@whitehouse.gov");
        testUser2.setPassword("president");
        testUser2.addAuthority("USER");
        userDao.addUser(testUser2);
        
         User testUser3 = new User();
        testUser3.setFirstName("Al");
        testUser3.setLastName("Gore");
        testUser3.setUsername("greenman");
        testUser3.setEmail("al@gore.com");
        testUser3.setPassword("president");
        testUser3.addAuthority("USER");
        userDao.addUser(testUser3);
        
        List<User> ret = userDao.list();
        
        Assert.assertEquals(3, ret.size());
    }
    
    @Test(expected = EmptyResultDataAccessException.class)
    public void deleteUser1(){
        userDao.addUser(testUser1);
        userDao.deleteUser(testUser1.getUsername());
        
        // should throw exception
        userDao.getByUsername(testUser1.getUsername());
        
    }
    
    @Test
    public void deleteUser2(){
        userDao.addUser(testUser1);
        userDao.deleteUser(testUser1.getUsername());
        
        Assert.assertEquals(0, jdbcTemplate.queryForList("SELECT id FROM authorities", Integer.class).size());
        Assert.assertEquals(0, jdbcTemplate.queryForList("SELECT id FROM users", Integer.class).size());
    }
    
    @Test
    public void updateTest1(){
        userDao.addUser(testUser1);
        
   
        testUser1.setFirstName("Bobby");
        testUser1.setLastName("Boucher");
        testUser1.setEmail("bboucher@gmail.com");
        testUser1.setPassword("waterboy");
        
        userDao.updateUser(testUser1);
        
        User res = userDao.getByUsername(testUser1.getUsername());
        
        Assert.assertEquals("Bobby", res.getFirstName());
        Assert.assertEquals("Boucher", res.getLastName());
        Assert.assertEquals("bboucher@gmail.com", res.getEmail());
        Assert.assertEquals("waterboy", res.getPassword());
        Assert.assertEquals(1, res.getAuthorities().size());
        
    }
}
