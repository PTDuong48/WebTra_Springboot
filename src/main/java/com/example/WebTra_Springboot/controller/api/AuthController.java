package com.example.WebTra_Springboot.controller.api;

import com.example.WebTra_Springboot.model.Role;
import com.example.WebTra_Springboot.model.User;
import com.example.WebTra_Springboot.repository.RoleRepository;
import com.example.WebTra_Springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");

        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // So sánh mật khẩu trực tiếp (do backend hiện tại chưa tích hợp Spring Security/BCrypt)
            if (user.getPassword().equals(password)) {
                return ResponseEntity.ok(Map.of(
                    "message", "Đăng nhập thành công",
                    "user", user
                ));
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Email hoặc mật khẩu không chính xác"));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
        String fullName = request.get("fullName");
        String email = request.get("email");
        String password = request.get("password");

        if (userRepository.findByEmail(email).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Email đã được sử dụng"));
        }

        User newUser = new User();
        newUser.setFullName(fullName);
        newUser.setEmail(email);
        newUser.setPassword(password); 

        // Gán Role mặc định: ROLE_USER
        Optional<Role> userRoleOpt = roleRepository.findByName("ROLE_USER");
        if (userRoleOpt.isPresent()) {
            newUser.setRoles(Set.of(userRoleOpt.get()));
        }

        userRepository.save(newUser);

        return ResponseEntity.ok(Map.of(
            "message", "Đăng ký thành công",
            "user", newUser
        ));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String newPassword = request.get("newPassword");
        
        Optional<User> userOpt = userRepository.findByEmail(email);
        
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setPassword(newPassword);
            userRepository.save(user);
            return ResponseEntity.ok(Map.of("message", "Đổi mật khẩu thành công. Vui lòng đăng nhập lại."));
        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Email không tồn tại trong hệ thống."));
    }
}
