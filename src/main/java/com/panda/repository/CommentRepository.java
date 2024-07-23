package com.panda.repository;

import com.panda.model.Comment;
import com.panda.model.FileTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {

//    @Query("FROM Comments c WHERE c.file_task_id = :fileTaskId")
    List<Comment> findAllByFileTaskId(FileTask fileTask);
}
