package org.credicore.user.services.impl;

import org.credicore.user.dto.request.RegistrationReqDto;
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

   @Override
   public User createUser(RegistrationReqDto register) {
      User user = new User();
      user.setFirstName(register.getFirstName());
      user.setLastName(register.getLastName());
      user.setEmail(register.getEmail());
      user.setPassword(register.getPassword());
      user.setPhoneNumber(register.getPhoneNumber());
      return saveUser(user);
   }

   @Override
   public User saveUser(User user) {
      return repository.save(user);
   }

}
