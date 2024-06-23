package com.gestone.gestone_api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/admin")
    public ResponseEntity<User> saveAdminUser(@RequestBody AdminUserRequestDTO adminUserRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveAdminUser(adminUserRequestDTO));
    }
}
