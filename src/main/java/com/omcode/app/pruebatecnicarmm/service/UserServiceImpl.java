package com.omcode.app.pruebatecnicarmm.service;

import com.omcode.app.pruebatecnicarmm.interfaceServices.IUserService;
import com.omcode.app.pruebatecnicarmm.entity.User;
import com.omcode.app.pruebatecnicarmm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return  userRepository.findAll();
    }

    @Override
    public Optional<User> getById(int id) {
        return Optional.empty();
    }
}
