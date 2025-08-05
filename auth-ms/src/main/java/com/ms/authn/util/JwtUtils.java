package com.ms.authn.util;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Component
public class JwtUtils {
  @Value("${security.jwt.key.private}")
  private String privateKey;

  @Value("${security.jwt.user.generator}")
  private String userGenerator;

  public String createToken(Authentication authentication) {
    var algorithm = Algorithm.HMAC256(this.privateKey);

    String username = authentication.getName();

    String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(","));

    return JWT.create().withIssuer(this.userGenerator).withSubject(username)
        .withClaim("authorities", authorities).withIssuedAt(new Date())
        .withExpiresAt(new Date(System.currentTimeMillis() + 1800000)).withJWTId(UUID.randomUUID().toString())
        .withNotBefore(new Date(System.currentTimeMillis())).sign(algorithm);
  }
}
