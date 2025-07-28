package com.abhi.training.demo.service;

import com.abhi.training.demo.model.User;
import com.abhi.training.demo.repository.UserRepository;
import com.abhi.training.demo.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public List<User> getAll() {
       return userRepository.findAll();
    }

    public void deleteUser(ObjectId myId) {
         userRepository.deleteById(myId);
    }

    public Optional<User> findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

}
