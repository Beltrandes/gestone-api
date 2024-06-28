package com.gestone.gestone_api.domain.user;

import com.gestone.gestone_api.auth.ApprovedEmployee;
import com.gestone.gestone_api.auth.ApprovedEmployeeRepository;
import com.gestone.gestone_api.domain.employee.EmployeeRepository;
import com.gestone.gestone_api.infra.security.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAdminService {
    @Autowired
    ApprovedEmployeeRepository approvedEmployeeRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    TokenService tokenService;

    public ApprovedEmployee approveEmployee(ApprovedEmployeeDTO data, HttpServletRequest request) {
        var token = request.getHeader("Authentication");
        var employee = employeeRepository.findById(data.employeeId());
        var user = userRepository.findUserByEmail(tokenService.getUserEmailFromToken(token));
        if (user.get().getMarbleshop() != employee.get().getMarbleshop()) {
            return null;
        }
        var approvedEmployee = new ApprovedEmployee();
        approvedEmployee.setEmail(data.email());
        approvedEmployee.setEmployee(employee.get());
        var savedApprovedEmployee = approvedEmployeeRepository.save(approvedEmployee);
        return savedApprovedEmployee;
    }

}
