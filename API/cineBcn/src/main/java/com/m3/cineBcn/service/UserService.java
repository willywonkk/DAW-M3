package com.m3.cineBcn.service;

import com.m3.cineBcn.model.User;
import com.m3.cineBcn.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public Optional<User> findById(int id) {
        return repository.findById(id);
    }

    public User save(User user) {
        return repository.save(user);
    }

    public Optional<User> update(int id, User user) {
        return repository.findById(id).map(u -> {
            u.setName(user.getName());
            u.setEmail(user.getEmail());
            return repository.save(u);
        });
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
