/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.daos;

import com.thesoftwareguild.flightmaster.models.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Writes flight data to a database using JDBC Template. This data is connected
 * to the requestor through a bridge table
 *
 * @author Yan
 */
public class UserDaoJdbcImpl implements UserDao {

    private static final String SQL_GET_LAST_ID = "SELECT LAST_INSERT_ID()";
    private static final String SQL_ADD_USER = "INSERT INTO users (username, password, first_name, last_name, email ) VALUES(?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_USER = "UPDATE users SET  password = ?, first_name = ?, last_name = ?, email = ? WHERE username = ?";
    private static final String SQL_DELETE_USER_BY_USERNAME = "DELETE FROM users WHERE username = ?"; 
    private static final String SQL_GET_USER_BY_USERNAME = "SELECT * FROM users WHERE username = ?";
    private static final String SQL_GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?";

    private static final String SQL_ADD_AUTHORITY = "INSERT INTO authorities (username, authority) VALUES (?,?)";
    private static final String SQL_DELETE_AUTHORITIES_BY_USERNAME = "DELETE FROM authorities WHERE username = ?";

    private static final String SQL_SELECT_AUTHORITIES = "SELECT authority FROM authorities WHERE username = ?";

    private static final String SQL_GET_ALL_USERS = "SELECT * FROM users";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public User getByUsername(String username) {
        return jdbcTemplate.queryForObject(SQL_GET_USER_BY_USERNAME, new UserMapper(), username);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public User getById(Integer id) {
        return jdbcTemplate.queryForObject(SQL_GET_USER_BY_ID, new UserMapper(), id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<User> list() {
        return jdbcTemplate.query(SQL_GET_ALL_USERS, new UserMapper());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public User addUser(User newUser) {
        // Add to users
        jdbcTemplate.update(SQL_ADD_USER,
                newUser.getUsername(),
                newUser.getPassword(),
                newUser.getFirstName(),
                newUser.getLastName(),
                newUser.getEmail());
        Integer id = jdbcTemplate.queryForObject(SQL_GET_LAST_ID, Integer.class);
        newUser.setUserId(id);
        addAuthoritiesToUser(newUser);
        return newUser;
    }

    @Transactional(propagation = Propagation.NESTED)
    private void addAuthoritiesToUser(User newUser) throws DataAccessException {
        
        for (String authority : newUser.getRoles()) {
            jdbcTemplate.update(SQL_ADD_AUTHORITY, newUser.getUsername(), authority);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUser(String username) {
        //Remove all authorities
        jdbcTemplate.update(SQL_DELETE_AUTHORITIES_BY_USERNAME, username);
        //Remove user
        jdbcTemplate.update(SQL_DELETE_USER_BY_USERNAME, username);
        
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUser(User user) {
        //Remove all authorities
        jdbcTemplate.update(SQL_DELETE_AUTHORITIES_BY_USERNAME, user.getUsername());
        //Update user
        jdbcTemplate.update(SQL_UPDATE_USER,
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getUsername());
                
        // Add authorities
        addAuthoritiesToUser(user);
                       
    }

    private final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {

            User user = new User();
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEnabled(rs.getInt("enabled") == 1 ? true : false);
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setEmail(rs.getString("email"));
            user.setUserId(rs.getInt("id"));

            List<String> authorities = jdbcTemplate.queryForList(SQL_SELECT_AUTHORITIES, String.class, user.getUsername());

            user.setRoles(authorities);

            return user;

        }

    }

}
