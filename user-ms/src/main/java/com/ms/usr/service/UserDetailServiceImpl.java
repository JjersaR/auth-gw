package com.ms.usr.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ms.usr.repository.IUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

  private final IUserRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    var usuarios = repository.findByName(username);
    List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

    usuarios.get().getRoles()
        .forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getNombre().name()))));

    usuarios.get().getRoles().stream().flatMap(role -> role.getPermisosList().stream())
        .forEach(permiso -> authorityList.add(new SimpleGrantedAuthority(permiso.getNombre())));

    return new User(usuarios.get().getName(), usuarios.get().getPassword(), usuarios.get().isEnabled(),
        usuarios.get().isAccountNoExpired(),
        usuarios.get().isCredentialNoExpired(), usuarios.get().isAccountNoLocked(), authorityList);
  }
}
