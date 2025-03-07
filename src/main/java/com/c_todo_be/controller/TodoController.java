package com.c_todo_be.controller;

import com.c_todo_be.entity.Todo;
import com.c_todo_be.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/todo")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> save(@RequestBody Todo todo) {
        return todoService.save(todo);
    }

    @GetMapping("/todos")
    public ResponseEntity<?> select() {
        return todoService.select();
    }

    @PutMapping("/todo/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") Long id, @RequestBody Todo todo) {
        return todoService.updateById(id, todo);
    }

    @DeleteMapping("/todo/{id}")
    public ResponseEntity<?> deleteTodoById(@PathVariable("id") Long todoId) {
        return todoService.deleteById(todoId);
    }
}
