package com.panda.controller;

import com.panda.dto.EmployeeDto;
import com.panda.service.AuthenticationService;
import com.panda.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/currents")
public class CurrentEmployeeController {

    private final EmployeeService employeeService;
    private final AuthenticationService authenticationService;

    @GetMapping("/current")
    public ResponseEntity<UserDetails> getUserDetails(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(userDetails);
    }

    @GetMapping("/current-employee-id")
    public EmployeeDto getEmployeeByLogin(Authentication authentication) {
        UUID id = authenticationService.getEmployeeIdByLogin(authentication.getName());
        return employeeService.getEmployeeById(id);
    }

//    public EmployeeDto getCurrent() {
//        System.out.println(employeeService.getCurrent() + "currentController");
//        return employeeService.getCurrent();
//    }
}
