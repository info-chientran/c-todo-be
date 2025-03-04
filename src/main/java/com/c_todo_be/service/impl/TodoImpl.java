package com.c_todo_be.service.impl;

import com.c_todo_be.entity.Todo;
import com.c_todo_be.repository.TodoRepository;
import com.c_todo_be.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class TodoImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public ResponseEntity<?> save(Todo todo) {
        try {
            todoRepository.save(todo);
            Map<String, Object> res = new HashMap<>();
            res.put("message", "Created");
            res.put("todo", todo);

            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> select() {
        List<Todo> todos = todoRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(todos);
    }

    @Override
    public ResponseEntity<?> selectById() {
        return null;
    }

    @Override
    public ResponseEntity<?> updateById(Long id, Todo todo) {
        try {
            Optional<Todo> existingTodo = todoRepository.findById(id);
            if (existingTodo.isPresent()) {
                Todo updatedTodo = existingTodo.get();
                updatedTodo.setTitle(todo.getTitle());
                updatedTodo.setCompleted(todo.isCompleted());

                Todo savedTodo = todoRepository.save(updatedTodo);
                Map<String, Object> res = new HashMap<>();
                res.put("message", "Updated");
                res.put("todo", savedTodo);

                return ResponseEntity.ok(res);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Todo not found with id: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> deleteById(Long id) {
        todoRepository.deleteById(id);
        Map<String, Object> res = new HashMap<>();
        res.put("message", "Deleted");
        res.put("id", id);

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
