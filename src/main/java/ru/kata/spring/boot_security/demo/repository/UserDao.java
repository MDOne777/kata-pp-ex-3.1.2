package ru.kata.spring.boot_security.demo.repository;

import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserDao {
    public List<User> getAll();
    public User get(int id);
    public void save(User user);
    public void delete(int id);
}
