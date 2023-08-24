package com.magadiflo.spring.data.jdbc.app.repositories;

import com.magadiflo.spring.data.jdbc.app.entities.Task;
import org.springframework.data.repository.ListCrudRepository;

public interface ITaskRepository extends ListCrudRepository<Task, Integer> {
}
