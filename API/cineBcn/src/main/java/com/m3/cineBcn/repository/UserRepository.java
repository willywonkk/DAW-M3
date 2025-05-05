package com.m3.cineBcn.repository;

import com.m3.cineBcn.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
