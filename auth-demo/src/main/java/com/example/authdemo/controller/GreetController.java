package com.example.authdemo.controller;

import com.example.authdemo.model.AuthenticatedUser;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/greet")
public class GreetController {

    private HttpSession session;
    private static final Logger logger = LoggerFactory.getLogger(GreetController.class);

    public GreetController(HttpSession session) {
        this.session = session;
    }

    @GetMapping
    public String greet() {
        AuthenticatedUser u = (AuthenticatedUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        String sessionId = session.getId();
        logger.info("User logged in with username: {} and session ID: {}", u.getUsername(), sessionId);

        return "Hi " + u.getUsername() + ", you are authenticated!";
    }

    @PostMapping("/update")
    public String updateResource() {
        AuthenticatedUser u = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "Hi " + u.getUsername() + ", you are allowed to update this resource!";
    }
}
