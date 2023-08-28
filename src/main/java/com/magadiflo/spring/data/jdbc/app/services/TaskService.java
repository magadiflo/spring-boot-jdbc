package com.magadiflo.spring.data.jdbc.app.services;

import com.magadiflo.spring.data.jdbc.app.entities.Task;
import com.magadiflo.spring.data.jdbc.app.repositories.ITaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
