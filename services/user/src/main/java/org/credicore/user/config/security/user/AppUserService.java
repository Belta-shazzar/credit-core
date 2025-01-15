package org.credicore.user.config.security.user;

import org.credicore.user.entities.User;
import org.credicore.user.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements UserDetailsService {
  private final UserRepository userRepo;

  public AppUserService(UserRepository userRepo) {
    this.userRepo = userRepo;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepo.findByEmail(email).orElseThrow(() ->
            new UsernameNotFoundException(String.format("user with %s %s not found", "email", email)));
    return new AppUser(user);
  }
}
