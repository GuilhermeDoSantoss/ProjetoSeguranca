package com.guilhermesantos.authdemo.repository;

import com.guilhermesantos.authdemo.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Integer> {

    public User findByLogin(String login);
}
