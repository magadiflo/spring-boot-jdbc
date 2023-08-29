package com.magadiflo.spring.data.jdbc.app.controllers;

import com.magadiflo.spring.data.jdbc.app.entities.Task;
import com.magadiflo.spring.data.jdbc.app.entities.dto.CommentUpdate;
import com.magadiflo.spring.data.jdbc.app.entities.dto.TaskCreate;
import com.magadiflo.spring.data.jdbc.app.entities.dto.TaskDetails;
import com.magadiflo.spring.data.jdbc.app.entities.dto.TaskUpdate;
import com.magadiflo.spring.data.jdbc.app.services.OwnerService;
import com.magadiflo.spring.data.jdbc.app.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;
    private final OwnerService ownerService;

    @GetMapping
    public ResponseEntity<List<Task>> findAllTasks() {
        return ResponseEntity.ok(this.taskService.findAllTasks());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Integer id) {
        return this.taskService.getTask(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/{id}/details")
    public ResponseEntity<TaskDetails> getTaskDetails(@PathVariable Integer id) {
        return this.taskService.getTask(id)
                .map(taskDB -> this.ownerService.getOwner(taskDB.getOwner().getId())
                        .map(ownerDB -> ResponseEntity.ok(new TaskDetails(taskDB, ownerDB)))
                        .orElseGet(() -> ResponseEntity.notFound().build())
                )
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskCreate taskCreate) {
        Task taskDB = this.taskService.createTask(taskCreate);
        URI uriTask = URI.create("/api/v1/tasks/" + taskDB.getId());
        return ResponseEntity.created(uriTask).body(taskDB);
    }

    @PostMapping(path = "/{id}/comment")
    public ResponseEntity<Task> taskAddComment(@PathVariable Integer id, @RequestBody CommentUpdate comment) {
        return this.taskService.taskAddComment(id, comment)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Integer id, @RequestBody TaskUpdate taskUpdate) {
        return this.taskService.updateTask(id, taskUpdate)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer id) {
        return this.taskService.deleteTask(id)
                .map(wasDeleted -> new ResponseEntity<Void>(HttpStatus.NO_CONTENT))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
