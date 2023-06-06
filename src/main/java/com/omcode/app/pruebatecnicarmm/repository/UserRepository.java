package com.omcode.app.pruebatecnicarmm.repository;

import com.omcode.app.pruebatecnicarmm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAll();
}
