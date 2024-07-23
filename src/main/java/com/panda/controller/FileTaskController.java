package com.panda.controller;

import com.panda.dto.FileTaskDto;
import com.panda.service.FileTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tasks")
public class FileTaskController {

    private final FileTaskService fileTaskService;

    @PostMapping("/add-task")
    public void addFileTask(@RequestBody FileTaskDto fileTaskDto) {
        fileTaskService.addFileTask(fileTaskDto);
    }

    @GetMapping("/all-tasks")
    public List<FileTaskDto> getAllTasks() {
            return fileTaskService.getAllTasks();
    }

    @GetMapping("/get-task-by-id/{id}")
    public FileTaskDto getTaskById(@PathVariable UUID id) {
        return fileTaskService.getFileTaskById(id);
    }
}
