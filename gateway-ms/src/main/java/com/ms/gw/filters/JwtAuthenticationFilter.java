package com.ms.gw.filters;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.ms.gw.util.JwtUtils;

import lombok.extern.slf4j.Slf4j;

@Component
public class JwtAuthenticationFilter extends AbstractGatewayFilterFactory<JwtAuthenticationFilter.Config> {

  private final JwtUtils jwtUtils;

  public JwtAuthenticationFilter(JwtUtils jwtUtils) {
    super(Config.class);
    this.jwtUtils = jwtUtils;
  }

  public static class Config {
  }

  @Override
  public GatewayFilter apply(Config config) {
    return (exchange, chain) -> {
      String token = exchange.getRequest()
          .getHeaders()
          .getFirst(HttpHeaders.AUTHORIZATION);

      if (token == null || !token.startsWith("Bearer ")) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
      }

      token = token.substring(7);

      try {
        DecodedJWT decodedJWT = jwtUtils.validateToken(token);

        ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
            .header("X-User-Id", decodedJWT.getSubject())
            .header("X-Authorities", jwtUtils.getSpecificClaim(decodedJWT, "authorities").asString())
            .build();

        ServerWebExchange mutatedExchange = exchange.mutate().request(mutatedRequest).build();

        return chain.filter(mutatedExchange);
      } catch (Exception e) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
      }
    };
  }

}
