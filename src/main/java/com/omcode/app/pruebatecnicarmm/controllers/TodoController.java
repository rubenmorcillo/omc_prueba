package com.omcode.app.pruebatecnicarmm.controllers;

import com.omcode.app.pruebatecnicarmm.entity.Todo;
import com.omcode.app.pruebatecnicarmm.entity.User;
import com.omcode.app.pruebatecnicarmm.service.TodoServiceImpl;
import com.omcode.app.pruebatecnicarmm.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping
public class TodoController {

    @Autowired
    private TodoServiceImpl todoService;
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/todo")
    public String findAll(@PageableDefault(page = 0, size = 10) Pageable pageable, Model model, @Param("searchTitle") String searchTitle, @Param("searchUser") String searchUser) {

        Page<Todo> pageTodo = todoService.findAll(pageable, searchTitle, searchUser);

        int totalPage = pageTodo.getTotalPages();

        if (totalPage > 0) {
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pages", pages);
        }
        String inverseDirection = "DESC";
        if (pageable.getSort().toString().contains("DESC")) {
            inverseDirection = "ASC";
        }

        pageTodo.getContent().forEach(content -> content.getUser());
        model.addAttribute("todoList", pageTodo.getContent());
        model.addAttribute("current", pageable.getPageNumber() + 1);
        model.addAttribute("next", pageable.getPageNumber() + 1);
        model.addAttribute("prev", pageable.getPageNumber() - 1);
        model.addAttribute("last", totalPage);
        model.addAttribute("actual", pageable.getPageNumber());
        model.addAttribute("inverseDirection", inverseDirection);

        return "getTodoView";

    }

    @GetMapping("/todo/form")
    public String create(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        model.addAttribute("todo", new Todo());
        return "createTodoForm";
    }

    @GetMapping("/todo/{todoId}")
    public ModelAndView edit(@PathVariable(name = "todoId") int id) {

        ModelAndView model = new ModelAndView("createTodoForm");
        Optional<Todo> optionalTodo = todoService.getById(id);
        Todo todo = null;
        if (optionalTodo.isPresent()) {

            todo = optionalTodo.get();
            model.addObject(todo);
        }
        List<User> users = userService.findAll();
        model.addObject("users", users);
        return model;

    }


    @PostMapping("/todo/{todoId}")
    public String save(@PathVariable(name = "todoId") int id, @Valid Todo todo, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            String errorMessage = allErrors.get(0).getDefaultMessage();
            model.addAttribute("todo", new Todo());
            model.addAttribute("error", errorMessage);
            return "createTodoForm";
        }
        if (id != 0) {
            todo.setId(id);
        }
        todoService.create(todo);
        return "redirect:/todo";
    }

    @GetMapping("/todo/del/{todoId}")
    public String delete(@PathVariable(name = "todoId") int id) {
        todoService.delete(id);
        return "redirect:/todo";
    }
}