package org.credicore.user.services;

import org.credicore.user.entities.User;

import java.util.Optional;

public interface UserService {
  Optional<User> getUserByEmail(String email);
}
