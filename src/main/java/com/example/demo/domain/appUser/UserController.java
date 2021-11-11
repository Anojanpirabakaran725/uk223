package com.example.demo.domain.appUser;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    //    ADD YOUR ENDPOINT MAPPINGS HERE
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/welcome")
    public ResponseEntity<String> HomeTest() {
        return ResponseEntity.ok().body("Hello World");
    }

    @GetMapping("/")
    public ResponseEntity<Collection<User>> findAll() {
        return new ResponseEntity<Collection<User>>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> findById(@PathVariable UUID id) throws InstanceNotFoundException {
        return new ResponseEntity<Optional<User>>(userService.findById(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<User> saveUser(@RequestBody User user) throws InstanceAlreadyExistsException {
        return ResponseEntity.ok().body(userService.saveUser(user));
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable UUID id, @RequestBody User user){
        return userService.updateUser(id, user);
    }
}
