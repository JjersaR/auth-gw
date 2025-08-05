package com.ms.usr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.usr.model.Role;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {

}
