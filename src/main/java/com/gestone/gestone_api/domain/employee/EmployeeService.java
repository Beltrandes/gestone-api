package com.gestone.gestone_api.domain.employee;

import com.gestone.gestone_api.domain.user.UserRepository;
import com.gestone.gestone_api.infra.security.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;

    public void saveEmployee(EmployeeRequestDTO employeeRequestDTO, HttpServletRequest request) {
        String token = request.getHeader("Authentication");
        if (token != null) {
            var bearerlessToken = token.substring(7);
            String userEmail = tokenService.getUserEmailFromToken(bearerlessToken);
            var user = userRepository.findUserByEmail(userEmail).get();
            var employee = new Employee();
            employee.setName(employeeRequestDTO.name());
            employee.setDocumentNumber(employeeRequestDTO.documentNumber());
            employee.setSalary(employeeRequestDTO.salary());
            employee.setMarbleshop(user.getMarbleshop());
            employee.setPhone(employeeRequestDTO.phone());
            employee.setEmployeeRole(employeeRequestDTO.employeeRole());
            employeeRepository.save(employee);
        }
    }


}
