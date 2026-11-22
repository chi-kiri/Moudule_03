package com.example.milkteaauth.service;

import com.example.milkteaauth.dto.UserResponse;
import com.example.milkteaauth.entity.User;
import com.example.milkteaauth.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public UserResponse getCurrentUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Không tìm thấy tài khoản"
                ));
        return toResponse(user);
    }

    @Transactional
    public UserResponse updateRole(Long id, String role, String adminEmail) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Không tìm thấy tài khoản"
                ));

        if (user.getEmail().equals(adminEmail)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "Không thể thay đổi quyền của chính mình"
            );
        }

        user.setRole(role);
        return toResponse(user);
    }

    private UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getPhone(), user.getEmail(), user.getRole());
    }
}
