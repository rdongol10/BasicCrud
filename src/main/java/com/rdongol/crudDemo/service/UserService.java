package com.rdongol.crudDemo.service;

import com.rdongol.crudDemo.entity.User;
import com.rdongol.crudDemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User addUser(User user) {
    return this.userRepository.save(user);
  }

  public User findById(Long id) {
    Optional<User> user = this.userRepository.findById(id);
    if (user.isPresent()) {
      return user.get();
    }

    return null;
  }

  public void deleteUser(Long userId) {
    User user = this.findById(userId);
    if (user == null) {
      throw new IllegalArgumentException("No user with id :" + userId);
    }

    this.userRepository.delete(user);
  }

  public List<User> findAll() {
    return (List<User>) this.userRepository.findAll();
  }

  public User update(Long userId, User updateUser) {
    if (this.findById(userId) == null) {
      throw new IllegalArgumentException("No user with id :" + userId);
    }

    updateUser.setId(userId);
    return this.userRepository.save(updateUser);
  }
}
