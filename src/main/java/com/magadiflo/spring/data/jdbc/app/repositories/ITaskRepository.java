package com.magadiflo.spring.data.jdbc.app.repositories;

import com.magadiflo.spring.data.jdbc.app.entities.Task;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface ITaskRepository extends ListCrudRepository<Task, Integer> {
    List<Task> findAllByOwner(Integer id);
}
