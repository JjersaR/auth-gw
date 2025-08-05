package com.ms.gw.util;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

@Component
public class JwtUtils {
  @Value("${security.jwt.key.private}")
  private String privateKey;

  @Value("${security.jwt.user.generator}")
  private String userGenerator;

  public DecodedJWT validateToken(String token) {
    try {
      var algorithm = Algorithm.HMAC256(this.privateKey);

      JWTVerifier verifier = JWT.require(algorithm).withIssuer(this.userGenerator).build();

      return verifier.verify(token);
    } catch (JWTVerificationException e) {
      throw new JWTVerificationException("Token invalid, not Authorized");
    }
  }

  public Claim getSpecificClaim(DecodedJWT decodedJWT, String claimName) {
    return decodedJWT.getClaim(claimName);
  }

  public Map<String, Claim> returnAllClaims(DecodedJWT decodedJWT) {
    return decodedJWT.getClaims();
  }

}
