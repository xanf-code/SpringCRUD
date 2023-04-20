package com.springlearning.springlearning.controller;

import com.springlearning.springlearning.exception.UserNotFoundException;
import com.springlearning.springlearning.model.User;
import com.springlearning.springlearning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    User addNewUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    @GetMapping("/get_users")
    List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/get_user/{id}")
    Optional<User> getUserByID(@PathVariable Long id) {
        try {
            return userRepository.findById(id);
        } catch (Exception e) {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }
    }

    @DeleteMapping("/delete_user/{id}")
    String deleteUserByID(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }
        userRepository.deleteById(id);
        return "User with ID " + id + " deleted.";
    }

    @GetMapping("/get_user/username/{value}")
    Optional<User> getByUsername(@PathVariable String value) {
        Optional<User> userOptional = userRepository.findByUsername(value);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            System.out.println(user);
            return Optional.of(user);
        } else {
            throw new UserNotFoundException("User with username " + value + " not found");
        }
    }

    @GetMapping("/get_occupation/{value}")
    Optional<List<User>> getByOccupation(@PathVariable String value){
        Optional<List<User>> userOptional = userRepository.findByOccupation(value);
        if(!userOptional.isPresent()){
            throw new UserNotFoundException("Users with occupation " + value + " not found.");
        }
        return userOptional;
    }
}
