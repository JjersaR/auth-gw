package com.ms.authn.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Auth {
  @Id
  private Long id;

  private String name;

  private String password;

  @Column(name = "account_no_expired")
  private Boolean accountNoExpired;

  @Column(name = "account_no_locked")
  private Boolean accountNoLocked;

  @Column(name = "credential_no_expired")
  private Boolean credentialNoExpired;

  @Column(name = "is_enabled")
  private Boolean isEnabled;

  @Column(name = "rol_name")
  private String rolName;
}
