package com.example.WebTra_Springboot.service;

import com.example.WebTra_Springboot.model.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User saveUser(User user);
    void deleteUser(Long id);
    Long getTotalUsers();
    User updateProfile(Long id, User userDetails);
    boolean changePassword(Long id, String oldPassword, String newPassword);
}
