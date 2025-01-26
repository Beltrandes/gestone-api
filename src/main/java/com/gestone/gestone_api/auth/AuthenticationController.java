package com.gestone.gestone_api.auth;

import com.gestone.gestone_api.domain.user.*;
import com.gestone.gestone_api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestone.gestone_api.domain.employee.EmployeeRegisterDTO;
import com.gestone.gestone_api.domain.marbleshop.Marbleshop;
import com.gestone.gestone_api.domain.marbleshop.MarbleshopService;

import jakarta.validation.Valid;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MarbleshopService marbleshopService;
    @Autowired
    private ApprovedAdminRepository approvedAdminRepository;
    @Autowired
    private ApprovedEmployeeRepository approvedEmployeeRepository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        var marbleshop = tokenService.getMarbleshopFromToken(token);
        return ResponseEntity.ok(new LoginResponseDTO(token, marbleshop.getId(), data.email()));
    }

    @PostMapping("/register/admin")
    public ResponseEntity registerAdmin(@RequestBody @Valid AdminRegisterDTO data) {
        if (approvedAdminRepository.findByEmail(data.email()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        if (userRepository.findByEmail(data.email()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        var adminUser = new User();
        adminUser.setName(data.name());
        adminUser.setEmail(data.email());
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        adminUser.setPassword(encryptedPassword);
        adminUser.setPhone(data.phone());
        adminUser.setType(UserType.ADMIN);

        var marbleshop = new Marbleshop(data.marbleshop().name(), data.marbleshop().email(), data.marbleshop().phone());
        marbleshop.getUsers().add(adminUser);
        var savedMarbleshop = marbleshopService.saveMarbleshop(marbleshop);
        adminUser.setMarbleshop(savedMarbleshop);

        userRepository.save(adminUser);

        return ResponseEntity.ok().build();
    }

    @PostMapping("register/employee")
    public ResponseEntity registerEmployee(@RequestBody EmployeeRegisterDTO data) {
        var approvedEmployeeResult = approvedEmployeeRepository.findByEmail(data.email());
        if (approvedEmployeeResult.isEmpty()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if (userRepository.findByEmail(data.email()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        var employeeUser = new User();
        employeeUser.setName(data.name());
        employeeUser.setEmail(data.email());
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        employeeUser.setPassword(encryptedPassword);
        employeeUser.setPhone(data.phone());
        employeeUser.setType(UserType.EMPLOYEE);

        var approvedEmployee = approvedEmployeeResult.get();
        employeeUser.setMarbleshop(approvedEmployee.getEmployee().getMarbleshop());

        userRepository.save(employeeUser);
        approvedEmployee.setRegistrationDate(LocalDateTime.now());
        approvedEmployee.setRegistered(true);
        approvedEmployeeRepository.save(approvedEmployee);

        return ResponseEntity.ok().build();

    }
}
