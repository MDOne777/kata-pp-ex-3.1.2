package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserService {
    public List<User> getAll();
    public User get(int id);
    public void save(User user);
    public void delete(int id);
}
