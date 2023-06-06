package com.omcode.app.pruebatecnicarmm.interfaceServices;

import com.omcode.app.pruebatecnicarmm.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    public List<User> findAll();
    public Optional<User> getById(int id);
}
