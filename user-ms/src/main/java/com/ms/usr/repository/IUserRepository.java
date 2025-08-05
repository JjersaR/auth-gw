package com.ms.usr.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.usr.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
  Optional<User> findByName(String name);
}
