package org.credicore.user.services;

import org.credicore.user.dto.request.RegistrationReqDto;
import org.credicore.user.entities.User;
import org.credicore.user.exception.custom.ConflictException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
  public final UserService userService;

  public AuthService(UserService userService) {
    this.userService = userService;
  }

  public String registerUser(RegistrationReqDto registerDto) throws ConflictException {
    Optional<User> checkUser = this.userService.getUserByEmail(registerDto.getEmail());

    if (checkUser.isPresent()) throw new ConflictException("User with email already exist");


    return "response";
  }

}
