package com.ms.authn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ms.authn.model.Auth;

@Repository
public interface IAuthRepository extends JpaRepository<Auth, Long> {
  @Query(value = """
      select u.id, u.name, u.password, u.account_no_expired, u.account_no_locked, u.credential_no_expired, u.is_enabled, r.nombre rol_name
      from user u
      join usuarios_roles ur on u.id = ur.usuario_id
      join role r on r.rol_id = ur.rol_id
      where u.name = :username
          """, nativeQuery = true)
  Optional<Auth> findByUsername(String username);
}
