package com.c_todo_be.service.impl;

import com.c_todo_be.entity.User;
import com.c_todo_be.repository.UserRepository;
import com.c_todo_be.security.jwt.JwtUtil;
import com.c_todo_be.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class AuthImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<?> signIn(HttpServletResponse httpServletResponse, String username, String password) {
        try {
            Map<String, Object> res = new HashMap<>();
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            if (!passwordEncoder.matches(password, user.getPassword())) {
                res.put("message", "Invalid credentials");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
            }

//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(username, password)
//            );
//            SecurityContextHolder.getContext().setAuthentication(authentication);


            String jwt = jwtUtil.generateToken(username);
            Cookie cookie = new Cookie("JWT", jwt);
            cookie.setPath("/");
            cookie.setAttribute("SameSite", "None");
            cookie.setHttpOnly(true);
            cookie.setMaxAge(60 * 60 * 24);

            res.put("message", "Successfully");
            httpServletResponse.addCookie(cookie);


            return ResponseEntity.status(HttpStatus.ACCEPTED).body(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}
