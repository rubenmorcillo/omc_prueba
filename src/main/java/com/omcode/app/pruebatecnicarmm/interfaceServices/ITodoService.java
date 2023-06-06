package com.omcode.app.pruebatecnicarmm.interfaceServices;

import com.omcode.app.pruebatecnicarmm.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ITodoService {

    Page<Todo> findAll(Pageable pageable, String title, String username);
    List<Todo> findAll();

    Optional<Todo> getById(int id);

    int create(Todo todo);

    void delete(int id);
}
