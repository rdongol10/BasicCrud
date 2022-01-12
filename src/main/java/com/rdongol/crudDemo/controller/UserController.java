package com.rdongol.crudDemo.controller;

import com.rdongol.crudDemo.entity.User;
import com.rdongol.crudDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> findUser(@PathVariable Long id) {
    return ResponseEntity.ok(this.userService.findById(id));
  }

  @PostMapping
  public ResponseEntity<User> addUser(@RequestBody User user) {
    return ResponseEntity.ok(userService.addUser(user));
  }

  @GetMapping()
  public ResponseEntity<List<User>> findAll() {
    return ResponseEntity.ok(this.userService.findAll());
  }

  @DeleteMapping("/{userId}")
  public ResponseEntity delete(@PathVariable Long userId) {
    this.userService.deleteUser(userId);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{userId}")
  public ResponseEntity<User> update(@RequestBody User user, @PathVariable Long userId) {
    return ResponseEntity.ok(this.userService.update(userId, user));
  }
}
