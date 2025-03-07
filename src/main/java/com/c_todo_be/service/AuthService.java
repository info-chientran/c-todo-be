package com.c_todo_be.service;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> signIn(HttpServletResponse httpServletResponse, String username, String password);
}
