package com.panda.service;

import com.panda.dto.EmployeeDto;
import com.panda.dto.FileTaskDto;
import com.panda.mapper.EmployeeMapper;
import com.panda.mapper.FileTaskMapper;
import com.panda.model.Employee;
import com.panda.model.FileTask;
import com.panda.repository.FileTaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class FileTaskService {

    private final FileTaskRepository fileTaskRepository;
    private final FileTaskMapper fileTaskMapper;
    private final EmployeeMapper employeeMapper;
    private final EmployeeService employeeService;

    /**
     * добавляет новую задачу и назначает сотрудников,
     * которые ее будут выполнять
     *
     * @param fileTaskDto
     * @return
     */
    @Transactional
    public void addFileTask(FileTaskDto fileTaskDto) {

        List<UUID> employeeIds = fileTaskDto.getExecutors().stream()
                .map(EmployeeDto::getId).toList();

        Set<Employee> employees = employeeService.getEmployees(employeeIds);

        FileTask entity = fileTaskMapper.toEntity(fileTaskDto);
//        entity.setExecutors(employees);

        FileTask fileTaskSaved = fileTaskRepository.save(entity);

//        FileTaskDto dto = fileTaskMapper.toDto(fileTaskSaved);
//        dto.setExecutors(employees.stream()
//                .map(employeeMapper::toDto)
//                .collect(Collectors.toSet()));
//
//        return dto;
    }

    /**
     * изменение задачи                 //???
     *
     * @param fileTaskDto
     * @return
     */
    @Transactional
    public FileTaskDto updateFileTask(FileTaskDto fileTaskDto) {

        FileTask fileTaskUpdate = fileTaskRepository.findById(fileTaskDto.getId()).orElseThrow();
        FileTask fileTaskSaved = fileTaskMapper.marge(fileTaskDto, fileTaskUpdate);

        fileTaskRepository.save(fileTaskSaved);

        return fileTaskMapper.toDto(fileTaskSaved);
    }

    public List<FileTaskDto> getAllTasks() {

        List<FileTaskDto> fileTasks = fileTaskRepository.findAll().stream()
                .map(fileTaskMapper::toDto)
                .toList();

        return fileTasks;
    }

    public FileTaskDto getFileTaskById(UUID fileTaskId) {

        FileTask fileTask = fileTaskRepository.findById(fileTaskId).orElseThrow();

        return fileTaskMapper.toDto(fileTask);
    }
}
