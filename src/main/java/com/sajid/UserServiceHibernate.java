package com.sajid;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceHibernate implements UserService {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<User> getAllUsers() {       // return all data from table
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        return session.createQuery("FROM User").list();
    }

    @Override
    public User getUserById(int id) {       // retrieve a row from table of given primary key
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = (User) session.get(User.class, id);
        session.getTransaction().commit();
        return user;
    }

    @Override
    public void addUser(User user) {        // add a new row/data in table
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
    }

    @Override
    public void deleteUser(int id) {        // removes a row from table of given primary key
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
    }

    @Override
    public void updateUser(int id, User user) {        // update all columns of a row
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User tempUser = session.get(User.class, id);
        tempUser.setName(user.getName());
        tempUser.setAge(user.getAge());
        tempUser.setEmail(user.getEmail());
        tempUser.setMobileNumber(user.getMobileNumber());
        session.save(tempUser);
        session.getTransaction().commit();
    }
}
