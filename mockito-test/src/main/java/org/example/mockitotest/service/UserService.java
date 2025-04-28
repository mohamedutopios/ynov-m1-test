package org.example.mockitotest.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.mockitotest.exception.InvalidEmailException;
import org.example.mockitotest.model.User;
import org.example.mockitotest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        if (!user.getEmail().contains("@")) {
            throw new InvalidEmailException("Invalid format email");
        }
        return userRepository.save(user);
    }

    public User updateUser(Long id, User user) {
        if (userRepository.findById(id).isPresent()) {
            user.setId(id);
            return userRepository.save(user);
        }else {
            throw new EntityNotFoundException("User not found");
        }
    }

    public void deleteUserById(Long id){
        if(userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);
        }else{
            throw new EntityNotFoundException("User not found");
        }


    }

}
