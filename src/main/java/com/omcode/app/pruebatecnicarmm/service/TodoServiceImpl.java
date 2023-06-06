package com.omcode.app.pruebatecnicarmm.service;

import com.omcode.app.pruebatecnicarmm.interfaceServices.ITodoService;
import com.omcode.app.pruebatecnicarmm.entity.Todo;
import com.omcode.app.pruebatecnicarmm.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements ITodoService {

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    UserServiceImpl userService;

    @Override
    public Page<Todo> findAll(Pageable pageable, String title, String username) {
        Page<Todo> page = null;
        if (title != null && !title.isEmpty()){
            page = todoRepository.findByTitleLike(pageable, title);
        }else if(username != null && !username.isEmpty()){
            System.out.println("SERVICIO: Voy a buscar usuario "+username);
            page = todoRepository.findByUsername(pageable,username);
        }else{
            page = todoRepository.findAll(pageable);

        }
        return page;
    }

    @Override
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }


    @Override
    public Optional<Todo> getById(int id) {
        return todoRepository.findById(id);
    }

    @Override
    public int create(Todo todo) {
        int response = 0;
        Todo newTodo = todoRepository.saveAndFlush(todo);
        if (newTodo != null) {
            response = 1;
        }
        return response;
    }

    @Override
    public void delete(int id) {
        if (todoRepository.findById(id).isPresent()) {
            todoRepository.delete(todoRepository.findById(id).get());
        }
        return;
    }


}
