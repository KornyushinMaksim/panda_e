package com.panda.controller;

import com.panda.dto.DepartmentDto;import com.panda.dto.DepartmentDto;
import com.panda.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping("/create-department")
    public void createDepartment(@RequestBody DepartmentDto departmentDto) {
        departmentService.addDepartment(departmentDto);
    }

    @GetMapping("/get-all-departments")
    public List<DepartmentDto> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    @PutMapping("/update-department")
    public DepartmentDto updateDepartment(@RequestBody DepartmentDto departmentDto) {
        return departmentService.updateDepartment(departmentDto);
    }

    @DeleteMapping("/delete-department{id}")
    public void deleteDepartment(@PathVariable UUID id) {
        departmentService.deleteDepartmentById(id);
    }
}
