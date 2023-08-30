package com.magadiflo.spring.data.jdbc.app.services;

import com.magadiflo.spring.data.jdbc.app.entities.Comment;
import com.magadiflo.spring.data.jdbc.app.entities.Task;
import com.magadiflo.spring.data.jdbc.app.entities.dto.CommentUpdate;
import com.magadiflo.spring.data.jdbc.app.entities.dto.TaskCreate;
import com.magadiflo.spring.data.jdbc.app.entities.dto.TaskUpdate;
import com.magadiflo.spring.data.jdbc.app.repositories.ITaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TaskService {
    private final ITaskRepository taskRepository;

    @Transactional(readOnly = true)
    public List<Task> findAllTasks() {
        return this.taskRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Task> getTask(Integer id) {
        return this.taskRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Task> findAllByOwner(Integer id) {
        return this.taskRepository.findAllByOwner(id);
    }

    @Transactional
    public Task createTask(TaskCreate taskCreate) {
        Task newTask = Task.builder()
                .title(taskCreate.title())
                .content(taskCreate.content())
                .publishedOn(LocalDateTime.now())
                .owner(AggregateReference.to(taskCreate.ownerId()))
                .build();
        return this.taskRepository.save(newTask);
    }

    @Transactional
    public Optional<Task> taskAddComment(Integer id, CommentUpdate commentUpdate) {
        return this.taskRepository.findById(id)
                .map(taskDB -> {
                    Comment newComment = Comment.builder()
                            .name(commentUpdate.name())
                            .content(commentUpdate.content())
                            .publishedOn(LocalDateTime.now())
                            .build();
                    taskDB.addComment(newComment);
                    return this.taskRepository.save(taskDB);
                });
    }

    @Transactional
    public Optional<Task> updateTask(Integer id, TaskUpdate taskUpdate) {
        return this.taskRepository.findById(id)
                .map(taskDB -> {

                    taskDB.setTitle(taskUpdate.title());
                    taskDB.setContent(taskUpdate.content());
                    taskDB.setUpdatedOn(LocalDateTime.now());
                    taskDB.setOwner(AggregateReference.to(taskUpdate.ownerId()));
                    taskDB.setComments(taskUpdate.comments());

                    return this.taskRepository.save(taskDB);
                });
    }

    @Transactional
    public Optional<Boolean> deleteTask(Integer id) {
        return this.taskRepository.findById(id)
                .map(taskDB -> {
                    this.taskRepository.deleteById(id);
                    return true;
                });
    }
}
