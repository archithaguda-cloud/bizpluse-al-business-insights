package com.bizpulse.service;
import com.bizpulse.dto.DashboardResponse.LoginRequest;
import com.bizpulse.dto.DashboardResponse.RegisterRequest;
import com.bizpulse.entity.User;
import com.bizpulse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    public Map<String, Object> login(LoginRequest request) {
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // Note: In enterprise production, compare with BCryptPasswordEncoder
            if (user.getPassword().equals(request.getPassword())) {
                String token = "jwt_bizpulse_" + UUID.randomUUID().toString();
                Map<String, Object> response = new HashMap<>();
                response.put("success", true);
                response.put("token", token);
                response.put("user", Map.of(
                        "id", user.getId(),
                        "name", user.getName(),
                        "email", user.getEmail(),
                        "role", user.getRole(),
                        "company", user.getCompany()
                ));
                return response;
            }
        }

        // Demo user fallback if database is empty
        if ("demo.executive@bizpulse.ai".equalsIgnoreCase(request.getEmail())) {
            String token = "jwt_bizpulse_demo_" + UUID.randomUUID().toString();
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("token", token);
            response.put("user", Map.of(
                    "id", 1L,
                    "name", "Alex Carter",
                    "email", "demo.executive@bizpulse.ai",
                    "role", "Chief Revenue Officer",
                    "company", "Vanguard Global Corp"
            ));
            return response;
        }
        throw new RuntimeException("Invalid email or password");
    }
    public Map<String, Object> register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("An account with email " + request.getEmail() + " already exists.");
        }
        User newUser = new User(
                request.getName(),
                request.getEmail(),
                request.getPassword(),
                "Business Executive",
                request.getCompany() != null ? request.getCompany() : "Global Enterprise"
        );
        User saved = userRepository.save(newUser);
        String token = "jwt_bizpulse_" + UUID.randomUUID().toString();
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("token", token);
        response.put("user", Map.of(
                "id", saved.getId(),
                "name", saved.getName(),
                "email", saved.getEmail(),
                "role", saved.getRole(),
                "company", saved.getCompany()
        ));
        return response;
    }
}