package com.example.WebTra_Springboot.service.impl;

import com.example.WebTra_Springboot.model.User;
import com.example.WebTra_Springboot.repository.UserRepository;
import com.example.WebTra_Springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Long getTotalUsers() {
        return userRepository.count();
    }

    @Override
    public User updateProfile(Long id, User userDetails) {
        User user = getUserById(id);
        if (user != null) {
            user.setFullName(userDetails.getFullName());
            user.setPhone(userDetails.getPhone());
            user.setAddress(userDetails.getAddress());
            user.setProvince(userDetails.getProvince());
            user.setDistrict(userDetails.getDistrict());
            user.setWard(userDetails.getWard());
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public boolean changePassword(Long id, String oldPassword, String newPassword) {
        User user = getUserById(id);
        if (user != null && user.getPassword().equals(oldPassword)) {
            user.setPassword(newPassword);
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
