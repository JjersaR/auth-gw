package com.ms.usr.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Permisos {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "permiso_ID")
  private Long permisoID;

  @Column(unique = true, nullable = false, updatable = false)
  private String nombre;
}
