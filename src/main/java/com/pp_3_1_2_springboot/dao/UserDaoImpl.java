package com.pp_3_1_2_springboot.dao;

import com.pp_3_1_2_springboot.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class)
                .getResultList();
    }

    @Override
    public void deleteUser(int id) {
        entityManager.createQuery("delete from User where id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getUser(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public Optional<User> getUser(String email) {
        User user = null;
        try {
            user = (User) entityManager.createQuery(
                            "SELECT c FROM User c WHERE c.email LIKE :email")
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (EmptyResultDataAccessException | NoResultException ignored) {}
        return Optional.ofNullable(user);
    }
}
