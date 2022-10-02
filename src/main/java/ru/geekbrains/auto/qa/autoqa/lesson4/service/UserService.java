package ru.geekbrains.auto.qa.autoqa.lesson4.service;


import org.apache.catalina.User;

public interface UserService {

    void save(User userDto);

    void update(User userDto);

    User getById(Integer id);

    void delete(Integer id);
}
