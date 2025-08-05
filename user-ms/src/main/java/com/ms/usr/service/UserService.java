package com.ms.usr.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ms.usr.model.User;
import com.ms.usr.repository.IUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

  private final IUserRepository repository;

  private final PasswordEncoder passwordEncoder;

  public Optional<User> findByName(String name) {
    return repository.findByName(name);
  }

  public void save(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    repository.save(user);
  }
}
