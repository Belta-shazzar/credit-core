package org.credicore.user.config.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.credicore.user.config.security.user.AppUserService;
import org.credicore.user.entities.User;
import org.credicore.user.services.impl.UserServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
  private final JwtUtils jwtUtils;
  private final AppUserService appUserService;
  private final UserServiceImpl userServiceImpl;

  public JwtFilter(JwtUtils jwtUtils, AppUserService appUserService,
                   UserServiceImpl userServiceImpl) {
    this.jwtUtils = jwtUtils;
    this.appUserService = appUserService;
    this.userServiceImpl = userServiceImpl;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {
    String authHeader = request.getHeader("Authorization");
    String tokenPrefix = "Bearer ";

    if (authHeader != null && authHeader.startsWith(tokenPrefix)) {
      String token = authHeader.substring(tokenPrefix.length());

      try {
        String username = jwtUtils.getUsernameFromToken(token);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
          UserDetails userDetails = appUserService.loadUserByUsername(username);

          if (jwtUtils.isValidToken(token, userDetails)) {
            User user = userServiceImpl.getUserByEmail(username).orElseThrow();

            // Create and set authentication
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);
          }
        }
      } catch (Exception e) {
        logger.error("Cannot set user authentication: {}");
      }
    }

    filterChain.doFilter(request, response);
  }
}
