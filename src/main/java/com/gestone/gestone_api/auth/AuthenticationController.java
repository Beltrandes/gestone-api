package com.gestone.gestone_api.auth;

import com.gestone.gestone_api.domain.user.*;
import com.gestone.gestone_api.domain.employee.EmployeeRegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data) {
        var response = authService.login(data);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register/admin")
    public ResponseEntity<Void> registerAdmin(@RequestBody @Valid AdminRegisterDTO data) {
        try {
            authService.registerAdmin(data);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            if (e.getMessage().contains("not approved")) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/register/employee")
    public ResponseEntity<Void> registerEmployee(@RequestBody @Valid EmployeeRegisterDTO data) {
        try {
            authService.registerEmployee(data);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            if (e.getMessage().contains("not approved")) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/register/validate-email")
    public ResponseEntity<Boolean> validateEmail(@RequestParam("email") String email) {
        if (authService.validateAdminEmail(email)) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);
    }
}
