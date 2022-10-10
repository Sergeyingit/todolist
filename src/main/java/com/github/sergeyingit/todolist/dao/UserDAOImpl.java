package com.github.sergeyingit.todolist.dao;

import com.github.sergeyingit.todolist.entity.Role;
import com.github.sergeyingit.todolist.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.QueryHints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

//import javax.persistence.Query;
import javax.persistence.QueryHint;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private PasswordEncoder encoder;


    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        List<User> allUsers = session.createQuery("from User", User.class).getResultList();
        return allUsers;
    }

    @Override
    public User findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);
        return user;
    }

    @Override
    public User findByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.createQuery("from User where email = :email", User.class)
                .setParameter("email", email).getResultList()
                .stream().findFirst().orElse(null);
        return user;
    }

    @Override
    public void saveUser(User user) {
        Session session = sessionFactory.getCurrentSession();

        user.setPassword(encoder.encode(user.getPasswordNew()));
        session.save(user);
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(user.getId(), "ROLE_USER"));
        user.setRoles(roles);
        user.setEnabled(true);
        session.save(user);
    }

    @Override
    public void deleteUser(int id) {
        Session session = sessionFactory.getCurrentSession();

        User user = session.get(User.class, id);
        session.delete(user);

    }

    @Override
    public List<User> getUserWithTasksForToday() {
        Session session = sessionFactory.getCurrentSession();
        LocalDate todayDate = LocalDate.now();
        List<User> users = session.createQuery("select distinct user from User user join fetch user.tasks task" +
                " where task.date = :todayDate", User.class).setParameter("todayDate", todayDate).setHint(QueryHints.PASS_DISTINCT_THROUGH, false).getResultList();

        return users;
    }
}
