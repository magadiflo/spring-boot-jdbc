package com.magadiflo.spring.data.jdbc.app.controllers;

import com.magadiflo.spring.data.jdbc.app.entities.Owner;
import com.magadiflo.spring.data.jdbc.app.entities.dto.OwnerDetails;
import com.magadiflo.spring.data.jdbc.app.entities.Task;
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

    @PostMapping
    public ResponseEntity<Owner> createOwner(@RequestBody Owner owner) {
        Owner ownerDB = this.ownerService.createOwner(owner);
        URI uriOwner = URI.create("/api/v1/owners/" + ownerDB.getId());
        return ResponseEntity.created(uriOwner).body(ownerDB);
    }

    @PostMapping(path = "/all")
    public ResponseEntity<List<Owner>> createListOwners(@RequestBody List<Owner> owners) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.ownerService.createListOwners(owners));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Owner> updateOwner(@PathVariable Integer id, @RequestBody Owner owner) {
        return this.ownerService.updateOwner(id, owner)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<Owner> patchOwner(@PathVariable Integer id, @RequestBody Owner owner) {
        return this.ownerService.patchOwner(id, owner)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Integer id) {
        return this.ownerService.deleteOwner(id)
                .map(wasDeleted -> new ResponseEntity<Void>(HttpStatus.NO_CONTENT))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllOwners() {
        this.ownerService.deleteAllOwners();
        return ResponseEntity.noContent().build();
    }

}
