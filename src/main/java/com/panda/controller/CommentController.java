package com.panda.controller;

import com.panda.dto.CommentDto;
import com.panda.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/add-comment")
    public CommentDto addComment(@RequestBody CommentDto commentDto) {
        return commentService.addComment(commentDto);
    }

    @GetMapping("/get-comment")
    public CommentDto getCommentById(@RequestParam UUID commentId) {
        return commentService.getCommentById(commentId);
    }

    @GetMapping("/get-comments-by-file-task-id/{fileTaskId}")
    public List<CommentDto> getCommentsByFileTaskId(@PathVariable UUID fileTaskId) {
        return commentService.getCommentsByFileTaskId(fileTaskId);
    }

    @PutMapping("/update-comment")
    public CommentDto updateComment(@RequestBody CommentDto commentDto) {
        return commentService.updateComment(commentDto);
    }

    @DeleteMapping("/delete-comment/{id}")
    public void deleteCommentById(@PathVariable UUID id) {
        commentService.deleteCommentById(id);
    }
}
