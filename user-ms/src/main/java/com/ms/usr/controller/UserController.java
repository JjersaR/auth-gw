package com.ms.usr.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.usr.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

  private final UserService service;

  @GetMapping
  public ResponseEntity<String> getUser(Authentication authentication) {
    String userId = authentication.getName();
    String authorities = authentication.getAuthorities().toString();
    return ResponseEntity.ok("User ID: " + userId + ", Roles: " + authorities);
  }

}
