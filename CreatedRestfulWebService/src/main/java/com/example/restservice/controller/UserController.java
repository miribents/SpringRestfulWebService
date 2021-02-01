package com.example.restservice.controller;

import com.example.restservice.exceptions.NotFoundException;
import com.example.restservice.exceptions.UsernameExistsException;
import com.example.restservice.model.User;
import com.example.restservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserService service;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) {
        User foundUser = null;
        try {
            foundUser = service.getUser(id);
        } catch (UsernameExistsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(foundUser);
    }

    @GetMapping
    public @ResponseBody List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User userDetails) {

        User createdUser = null;
        try {
            createdUser = service.createUser(userDetails);
        } catch (UsernameExistsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {

        User updatedUser = null;

        try {
            updatedUser = service.updateUser(id, userDetails);
        } catch (UsernameExistsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        User user = null;
        try {
            user = service.deleteUser(id);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(user);
    }

}

