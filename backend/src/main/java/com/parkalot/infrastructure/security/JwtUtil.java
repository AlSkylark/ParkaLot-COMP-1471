package com.parkalot.infrastructure.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

  private static final String SECRET = "your-secret-key-change-this-in-prod";
  private static final long EXPIRY_MS = 86_400_000; // 24 hours

  private SecretKey key() {
    return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
  }

  public String generate(String username) {
    return Jwts.builder()
      .setSubject(username)
      .setIssuedAt(new Date())
      .setExpiration(new Date(System.currentTimeMillis() + EXPIRY_MS))
      .signWith(key())
      .compact();
  }

  public String extractUsername(String token) {
    return Jwts.parserBuilder()
      .setSigningKey(key())
      .build()
      .parseClaimsJws(token)
      .getBody()
      .getSubject();
  }

  public boolean isValid(String token) {
    try {
      extractUsername(token); // throws if expired or invalid
      return true;
    } catch (JwtException e) {
      return false;
    }
  }
}
