package com.gestone.gestone_api.domain.user;

import com.gestone.gestone_api.auth.ApprovedEmployee;
import com.gestone.gestone_api.auth.ApprovedEmployeeRepository;
import com.gestone.gestone_api.domain.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAdminService {
    @Autowired
    ApprovedEmployeeRepository approvedEmployeeRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    public void approveEmployee(ApprovedEmployeeDTO data) {
        var approvedEmployee = new ApprovedEmployee();
        approvedEmployee.setEmail(data.email());
        var employee = employeeRepository.findById(data.employeeId());
        approvedEmployee.setEmployee(employee.get());
        approvedEmployeeRepository.save(approvedEmployee);
    }

}
