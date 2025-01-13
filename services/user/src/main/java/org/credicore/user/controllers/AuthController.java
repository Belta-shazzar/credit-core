package org.credicore.user.controllers;

import org.credicore.user.dto.request.RegistrationReqDto;
import org.credicore.user.exception.custom.ConflictException;
import org.credicore.user.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {
  public final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("register")
  public ResponseEntity<String> registerUser(@RequestBody RegistrationReqDto register) throws ConflictException {
    return ResponseEntity.status(200).body(authService.registerUser(register));
  }
}
