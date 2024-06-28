package com.gestone.gestone_api.domain.employee;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> saveEmployee(@RequestBody EmployeeRequestDTO employeeRequestDTO, HttpServletRequest request) {
        var employee = employeeService.saveEmployee(employeeRequestDTO, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(EmployeeResponseDTO.fromEmployee(employee));
    }
}
