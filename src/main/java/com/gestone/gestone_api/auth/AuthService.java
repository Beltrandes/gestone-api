package com.gestone.gestone_api.auth;

import com.gestone.gestone_api.domain.employee.EmployeeRegisterDTO;
import com.gestone.gestone_api.domain.marbleshop.Marbleshop;
import com.gestone.gestone_api.domain.marbleshop.MarbleshopService;
import com.gestone.gestone_api.domain.user.*;
import com.gestone.gestone_api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class AuthService {

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

    public LoginResponseDTO login(AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        var marbleshop = tokenService.getMarbleshopFromToken(token);
        return new LoginResponseDTO(token, marbleshop.getId(), marbleshop.getName(), data.email());
    }

    @Transactional
    public void registerAdmin(AdminRegisterDTO data) {
        if (approvedAdminRepository.findByEmail(data.email()).isEmpty()) {
            throw new RuntimeException("Email not approved for admin registration");
        }

        if (userRepository.findByEmail(data.email()) != null) {
            throw new RuntimeException("User already exists");
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
    }

    @Transactional
    public void registerEmployee(EmployeeRegisterDTO data) {
        var approvedEmployeeResult = approvedEmployeeRepository.findByEmail(data.email());
        if (approvedEmployeeResult.isEmpty()) {
            throw new RuntimeException("Email not approved for employee registration");
        }
        if (userRepository.findByEmail(data.email()) != null) {
            throw new RuntimeException("User already exists");
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
    }

    public boolean validateAdminEmail(String email) {
        boolean isApproved = approvedAdminRepository.findByEmail(email).isPresent();
        boolean notRegistered = userRepository.findByEmail(email) == null;
        return isApproved && notRegistered;
    }
}
