package org.credicore.user.services.impl;

import org.credicore.user.entities.User;
import org.credicore.user.repositories.UserRepository;
import org.credicore.user.services.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
  public final UserRepository repository;

  public UserServiceImpl(UserRepository repository) {
    this.repository = repository;
  }

  @Override
  public Optional<User> getUserByEmail(String email) {
    return this.repository.findByEmail(email);
  }
}
