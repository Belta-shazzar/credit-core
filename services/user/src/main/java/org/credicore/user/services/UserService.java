package org.credicore.user.services;

import org.credicore.user.dto.request.RegistrationReqDto;
import org.credicore.user.entities.User;

import java.util.Optional;

public interface UserService {
   Optional<User> getUserByEmail(String email);

   User createUser(RegistrationReqDto register);

   User saveUser(User user);
}
