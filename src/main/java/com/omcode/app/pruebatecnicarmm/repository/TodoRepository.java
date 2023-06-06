package com.omcode.app.pruebatecnicarmm.repository;

import com.omcode.app.pruebatecnicarmm.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
    List<Todo> findAll();
    @Query("SELECT t FROM Todo t WHERE t.title LIKE %?1%")
    Page<Todo> findByTitleLike(Pageable pageable, String title);

    @Query("SELECT t FROM Todo t JOIN t.user u WHERE u.username = :username")
    Page<Todo> findByUsername(Pageable pageable, String username);

}
