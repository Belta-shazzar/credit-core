package org.credicore.user.config.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.credicore.user.config.security.user.AppUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtils {

  public boolean isValidToken(String token, UserDetails user) {
    final String username = getUsernameFromToken(token);

    return (username.equals(user.getUsername()) && !isTokenExpired(token));
  }

  public String getUsernameFromToken(String token) {
    return getClaimFromToken(token, Claims::getSubject);
  }

  private boolean isTokenExpired(String token) {
    return getTokenExpirationTime(token).before(new Date());
  }

  private Date getTokenExpirationTime(String token) {
    return getClaimFromToken(token, Claims::getExpiration);
  }

  public <T> T getClaimFromToken(String token, Function<Claims, T> resolver) {
    Claims claims = extractClaims(token);
    return resolver.apply(claims);
  }

  private Claims extractClaims(String token) {
    return Jwts
            .parser()
            .verifyWith(getAccessTokenSecretKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
  }

  public String generateJwtToken(AppUser user, JwtType jwtType) {
    //  @Value("${spring.app.jwt.refreshValidity}")
    long refreshTokenValidity = 604800000;
    //  @Value("${spring.app.jwt.accessValidity}")
    long accessTokenValidity = 86400000;
    long validity = jwtType == JwtType.REFRESH ? refreshTokenValidity :
            accessTokenValidity;

    SecretKey secret = jwtType == JwtType.REFRESH ?
            getRefreshTokenSecretKey() :
            getAccessTokenSecretKey();

    return Jwts.builder()
            .subject(user.getUsername())
            .expiration(new Date((System.currentTimeMillis() + validity)))
            .signWith(secret)
            .compact();
  }

  public String generateJwtToken(AppUser user) {
    return generateJwtToken(user, JwtType.ACCESS);
  }

  public SecretKey getAccessTokenSecretKey() {
    //  @Value("${spring.app.jwt.secret}")
    String accessTokenSecret = "QmFzZTY0RW5jb2RlZFNlY3JldEtleVZhbHVl0RW5JldEtleVFzZTY0eVZhbHVl0RW5";
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(accessTokenSecret));
  }

  public SecretKey getRefreshTokenSecretKey() {
    //  @Value("${spring.app.jwt.secret}")
    String refreshTokenSecret = "QmFzZTY0RW5jb2RlZFNlY3JldEtleVZhbHVl0RW5JldEtleVFzZTY0eVZhbHVl0RW5";
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(refreshTokenSecret));
  }
}
