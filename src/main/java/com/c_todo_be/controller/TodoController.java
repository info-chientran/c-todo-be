package com.c_todo_be.controller;

import com.c_todo_be.entity.Todo;
import com.c_todo_be.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping("/todo")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> save(@RequestBody Todo todo) {
        return todoService.save(todo);
    }

    @GetMapping("/todos")
    public ResponseEntity<?> select() {
        return todoService.select();
    }

    @PostMapping("/todo/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") Long id, @RequestBody Todo todo) {
        return todoService.updateById(id, todo);
    }

    @DeleteMapping("/todo/{id}")
    public ResponseEntity<?> deleteTodoById(@PathVariable("id") Long todoId) {
        return todoService.deleteById(todoId);
    }
}
