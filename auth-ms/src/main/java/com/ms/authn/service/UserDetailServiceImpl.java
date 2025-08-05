package com.ms.authn.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ms.authn.repository.IAuthRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

  private final IAuthRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    var user = repository.findByUsername(username);
    List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

    user.ifPresent(us -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(us.getRolName()))));

    return new User(user.get().getName(), user.get().getPassword(), user.get().getIsEnabled(),
        user.get().getAccountNoExpired(), user.get().getCredentialNoExpired(), user.get().getAccountNoLocked(),
        authorityList);
  }

}
