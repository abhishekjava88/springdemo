package com.abhi.training.demo.controller;

import com.abhi.training.demo.model.User;
import com.abhi.training.demo.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping(value = "/getAllUsers")
    public List<User> getAllUsers(){
        return userService.getAll();
    }

    @PostMapping(value = "/addUser")
    public User createUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @PutMapping(value = "/updateUser")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        Optional<User> dbUser = userService.findByUserName(user.getUserName());
        if (dbUser.isPresent()) {
            User existingUser = dbUser.get();
            // Update the fields you want to allow update
            existingUser.setPassword(user.getPassword());
            // Add more fields as needed
            User updatedUser = userService.saveUser(existingUser);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteUser/{myId}")
    public void deleteJournalEntryById(@PathVariable ObjectId myId) {
        userService.deleteUser(myId);
    }

}
