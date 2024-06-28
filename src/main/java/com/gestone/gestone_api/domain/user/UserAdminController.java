package com.gestone.gestone_api.domain.user;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user/admin")
public class UserAdminController {
    @Autowired
    private UserAdminService userAdminService;

    @PostMapping("/approve")
    public ResponseEntity approveEmployeeToRegister(@RequestBody ApprovedEmployeeDTO approvedEmployeeDTO, HttpServletRequest request) {
        userAdminService.approveEmployee(approvedEmployeeDTO, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
