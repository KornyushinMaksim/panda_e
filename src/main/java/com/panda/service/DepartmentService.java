package com.panda.service;

import com.panda.dto.DepartmentDto;
import com.panda.mapper.DepartmentMapper;
import com.panda.model.Department;
import com.panda.repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    /**
     * добавление сущности department в БД
     */
    @Transactional
    public void addDepartment(DepartmentDto departmentDto) {

        Department department = Department.builder()
                .name(departmentDto.getName())
                .build();

        departmentRepository.save(department);
    }

    /**
     * вывод списка отделов
     * @return
     */
    public List<DepartmentDto> getAllDepartments() {

        return departmentRepository.findAll().stream()
                .map(departmentMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * редактирование отдела
     * @param departmentDto
     * @return
     */
    @Transactional
    public DepartmentDto updateDepartment(DepartmentDto departmentDto) {

        Department department = departmentRepository.findById(departmentDto.getId()).orElseThrow();
        Department savedDepartment = departmentRepository.save(departmentMapper.merge(departmentDto, department));

        return departmentMapper.toDto(savedDepartment);
    }

    /**
     * удаление отдела по id
     * @param id
     */
    @Transactional
    public void deleteDepartmentById(UUID id) {

        Department department = departmentRepository.findById(id).orElseThrow();

        departmentRepository.delete(department);
    }

    public DepartmentDto getById(UUID id) {

        Department department = departmentRepository.findById(id).orElseThrow();

        return departmentMapper.toDto(department);
    }
}
