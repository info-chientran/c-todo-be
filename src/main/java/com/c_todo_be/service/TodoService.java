package com.c_todo_be.service;

import com.c_todo_be.entity.Todo;
import org.springframework.http.ResponseEntity;

public interface TodoService {
    ResponseEntity<?> save(Todo todo);

    ResponseEntity<?> select();

    ResponseEntity<?> selectById();

    ResponseEntity<?> updateById(Long id, Todo todo);

    ResponseEntity<?> deleteById(Long id);
}
