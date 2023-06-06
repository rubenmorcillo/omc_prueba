package com.omcode.app.pruebatecnicarmm.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 1, max = 200, message="El Título debe contener entre 1 y 200 caracteres")
    private String title;
    private boolean completed;
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    private User user;

    public Todo() {
    }

    public Todo(int id, String title, boolean completed, User user) {
        this.id = id;
        this.title = title;
        this.completed = completed;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
