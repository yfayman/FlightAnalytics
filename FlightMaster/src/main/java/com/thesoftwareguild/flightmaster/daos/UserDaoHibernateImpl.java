/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.daos;

import com.thesoftwareguild.flightmaster.models.User;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author yan
 */
public class UserDaoHibernateImpl implements UserDao {
    
    private SessionFactory sessionFactory;
    
    @Override
    public User getByUsername(String username) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria userCrit = session.createCriteria(User.class);
        User user = (User) userCrit.add(Restrictions.eq("username", username)).uniqueResult();
        session.close();
        return user;
    }
    
    @Override
    public User getById(Integer id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = (User) session.load(User.class, id);
        session.close();
        return user;
    }
    
    @Override
    public List<User> list() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<User> users = session.createCriteria(User.class).list();
        session.close();
        return users;
    }
    
    @Override
    public User addUser(User newUser) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(newUser);
        session.getTransaction().commit();
        session.close();
        return newUser;
    }
    
    @Override
    public void deleteUser(String username) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "DELETE from users WHERE username = :username";
        session.createQuery(hql).setString("username", username).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
    
    @Override
    public void updateUser(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(user);
        session.getTransaction().commit();
        session.close();
    }
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
}
