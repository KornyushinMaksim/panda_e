package com.panda.service;

import com.panda.dto.CommentDto;
import com.panda.mapper.CommentMapper;
import com.panda.mapper.FileTaskMapper;
import com.panda.model.Comment;
import com.panda.model.FileTask;
import com.panda.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final FileTaskService fileTaskService;
    private final FileTaskMapper fileTaskMapper;

    /**
     * добавление комментария
     * @param commentDto
     * @return
     */
    @Transactional
    public CommentDto addComment(CommentDto commentDto) {

        Comment commentSaved = commentMapper.toEntity(commentDto);
        commentSaved.setDateComment(LocalDateTime.now());
        if (commentSaved.getComment().isEmpty()) {
            return null;
        }
        commentRepository.save(commentSaved);

        return commentMapper.toDto(commentSaved);
    }

    /**
     * редактирование комментария
     * @param commentDto
     * @return
     */
    public CommentDto updateComment(CommentDto commentDto) {

        Comment updateComment = commentRepository.findById(commentDto.getId()).orElseThrow();
        updateComment.setDateComment(LocalDateTime.now());

        Comment commentSaved = commentRepository.save(commentMapper.marge(commentDto, updateComment));

        return commentMapper.toDto(commentSaved);
    }

    /**
     *  получение комментария по id
     * @param commentId
     * @return
     */
    public CommentDto getCommentById(UUID commentId) {

        Comment comment = commentRepository.findById(commentId).orElseThrow();

        return commentMapper.toDto(comment);
    }

    /**
     * удаление комментария по id
     * @param id
     */
    public void deleteCommentById(UUID id) {
        commentRepository.deleteById(id);
    }

    /**
     *  получение списка по fileTaskId
     * @param fileTaskId
     * @return
     */
    public List<CommentDto> getCommentsByFileTaskId(UUID fileTaskId) {

        FileTask fileTask = fileTaskMapper.toEntity(fileTaskService.getFileTaskById(fileTaskId));
        List<Comment> commentsByFileTaskId = commentRepository.findAllByFileTaskId(fileTask);

        return commentsByFileTaskId.stream()
                .map(commentMapper::toDto)
                .toList();
    }
}
