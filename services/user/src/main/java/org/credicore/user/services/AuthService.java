package org.credicore.user.services;

import org.credicore.user.dto.request.RegistrationReqDto;
import org.credicore.user.entities.User;
import org.credicore.user.exception.custom.ConflictException;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {
   public final UserService userService;
   private final PasswordEncoder passwordEncoder;

   public AuthService(UserService userService, PasswordEncoder passwordEncoder) {
      this.userService = userService;
      this.passwordEncoder = passwordEncoder;
   }

   public String registerUser(RegistrationReqDto registerDto) throws ConflictException {
      Optional<User> checkUser = this.userService.getUserByEmail(registerDto.getEmail());

      if (checkUser.isPresent()) throw new ConflictException("User with email already exist");

//    hash password
      registerDto.setPassword(passwordEncoder.encode(registerDto.getPassword()));

      User user = this.userService.createUser(registerDto);
      String token = cacheToken(user.getId());

//      redisTemplate.opsForValue().set(String.format("email-verification:%s", token), user.getId());
//      String value = redisTemplate.opsForValue().get(String.format("email-verification:%s", "xGCk5Q7CTPFt7oXAXULX7MZqqO8QwB7KG7_1PfXqcYI=")).toString();

//      Generate authentication token
//      Cache the authentication token
//      publish event to send account confirmation email
//      return response message

      return String.format("The verification token is: %s", token);
   }

   public static String generateToken() {
      SecureRandom random = new SecureRandom();
      Base64.Encoder base64Encoder = Base64.getUrlEncoder();
      byte[] randomBytes = new byte[32];
      random.nextBytes(randomBytes);
      return base64Encoder.encodeToString(randomBytes);
   }

   @CachePut(value = "authCache", key = "'Shazzar-eh:' + #authToken")
   public String cacheToken(UUID userId) {
      String authToken = generateToken();

      System.out.println("The generated token: " + authToken);
      System.out.println("The user id: " + userId);

      return authToken;
   }

}
