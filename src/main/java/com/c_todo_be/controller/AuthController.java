package com.c_todo_be.controller;

import com.c_todo_be.dto.SignInDTO;
import com.c_todo_be.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class AuthController {


    private final AuthService authService;


    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(HttpServletResponse httpServletResponse, @RequestBody SignInDTO signInDTO) {


        return authService.signIn(httpServletResponse, signInDTO.getUsername(), signInDTO.getPassword());
    }
}
