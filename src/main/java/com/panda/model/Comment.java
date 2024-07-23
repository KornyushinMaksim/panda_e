package com.panda.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "file_task_id")
    private FileTask fileTaskId;

    @OneToOne
    @JoinColumn(name = "author_comment")
    private Employee authorComment;

    @Column(name = "comment")
    private String comment;

    @Column(name = "date_comment")
    private LocalDateTime dateComment;
}
