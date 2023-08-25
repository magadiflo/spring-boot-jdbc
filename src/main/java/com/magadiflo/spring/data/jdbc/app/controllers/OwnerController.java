package com.magadiflo.spring.data.jdbc.app.controllers;

import com.magadiflo.spring.data.jdbc.app.entities.Owner;
import com.magadiflo.spring.data.jdbc.app.entities.dto.OwnerDetails;
import com.magadiflo.spring.data.jdbc.app.entities.Task;
import com.magadiflo.spring.data.jdbc.app.services.OwnerService;
import com.magadiflo.spring.data.jdbc.app.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/owners")
public class OwnerController {

    private final OwnerService ownerService;
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Owner>> getOwners() {
        return ResponseEntity.ok(this.ownerService.getAllOwners());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Owner> getOwner(@PathVariable Integer id) {
        return this.ownerService.getOwner(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/{id}/details")
    public ResponseEntity<OwnerDetails> getOwnerDetails(@PathVariable Integer id) {
        return this.ownerService.getOwner(id)
                .map(ownerDB -> {
                    List<Task> tasks = this.taskService.findAllByOwner(id);
                    return ResponseEntity.ok(new OwnerDetails(ownerDB, tasks));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
