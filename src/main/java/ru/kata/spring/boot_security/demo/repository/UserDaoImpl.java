package ru.kata.spring.boot_security.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entity.User;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<User> getAll() {
        return this.entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User get(int id) {
        return this.entityManager.find(User.class, id);
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public void delete(int id) {
        this.entityManager
                .createQuery("delete from User where id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
