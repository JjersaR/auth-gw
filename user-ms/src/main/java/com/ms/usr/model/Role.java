package com.ms.usr.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "Rol_ID", unique = true)
  private long rolID;

  @Column(nullable = false, length = 50, unique = true)
  @Enumerated(EnumType.STRING)
  private ERole nombre;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(name = "roles_permisos", joinColumns = @JoinColumn(name = "Rol_ID"), inverseJoinColumns = @JoinColumn(name = "Permiso_ID"))
  private Set<Permisos> permisosList = new HashSet<>();
}
