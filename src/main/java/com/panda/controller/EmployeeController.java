package com.panda.controller;

import com.panda.dto.EmployeeDto;
import com.panda.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    public final EmployeeService employeeService;

    @GetMapping("/get-all-employees")
    public List<EmployeeDto> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/get-employee-by-id/{id}")
    public EmployeeDto getEmployeeById(@PathVariable UUID id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/update-employee")
    public EmployeeDto updateEmployee(@RequestBody EmployeeDto employeeDto) {
        return employeeService.updateEmployee(employeeDto);
    }

//    @GetMapping("/get-by-nickname")
//    public EmployeeDto getEmployeeByNickname(@RequestBody String nickname) {
//        return employeeService.getEmployeeByNickname(nickname);
//    }
}

