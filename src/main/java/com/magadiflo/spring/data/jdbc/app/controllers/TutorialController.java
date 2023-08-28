package com.magadiflo.spring.data.jdbc.app.controllers;

import com.magadiflo.spring.data.jdbc.app.entities.Tutorial;
import com.magadiflo.spring.data.jdbc.app.services.TutorialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/tutorials")
public class TutorialController {

    private final TutorialService tutorialService;

    @GetMapping
    public ResponseEntity<List<Tutorial>> getTutorials() {
        return ResponseEntity.ok(this.tutorialService.getAllTutorials());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Tutorial> getTutorial(@PathVariable Integer id) {
        return this.tutorialService.getTutorial(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
        Tutorial tutorialDB = this.tutorialService.createTutorial(tutorial);
        URI uriTutorial = URI.create("/api/v1/tutorials/" + tutorialDB.getId());
        return ResponseEntity.created(uriTutorial).body(tutorialDB);
    }

    @PostMapping(path = "/all")
    public ResponseEntity<List<Tutorial>> createListTutorials(@RequestBody List<Tutorial> tutorials) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.tutorialService.createListTutorials(tutorials));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable Integer id, @RequestBody Tutorial tutorial) {
        return this.tutorialService.updateTutorial(id, tutorial)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<Tutorial> patchTutorial(@PathVariable Integer id, @RequestBody Tutorial tutorial) {
        return this.tutorialService.patchTutorial(id, tutorial)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteTutorial(@PathVariable Integer id) {
        return this.tutorialService.deleteTutorial(id)
                .map(wasDeleted -> new ResponseEntity<Void>(HttpStatus.NO_CONTENT))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllTutorials() {
        this.tutorialService.deleteAllTutorials();
        return ResponseEntity.noContent().build();
    }
}
