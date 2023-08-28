package com.magadiflo.spring.data.jdbc.app.services;

import com.magadiflo.spring.data.jdbc.app.entities.Tutorial;
import com.magadiflo.spring.data.jdbc.app.repositories.ITutorialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TutorialService {

    private final ITutorialRepository tutorialRepository;

    @Transactional(readOnly = true)
    public List<Tutorial> getAllTutorials() {
        return this.tutorialRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Tutorial> getTutorial(Integer id) {
        return this.tutorialRepository.findById(id);
    }

    @Transactional
    public Tutorial createTutorial(Tutorial tutorial) {
        return this.tutorialRepository.save(tutorial);
    }

    @Transactional
    public List<Tutorial> createListTutorials(List<Tutorial> tutorials) {
        return this.tutorialRepository.saveAll(tutorials);
    }

    @Transactional
    public Optional<Tutorial> updateTutorial(Integer id, Tutorial tutorial) {
        return this.tutorialRepository.findById(id)
                .map(tutorialDB -> {
                    tutorialDB.setTitle(tutorial.getTitle());
                    return this.tutorialRepository.save(tutorialDB);
                });
    }

    @Transactional
    public Optional<Tutorial> patchTutorial(Integer id, Tutorial tutorial) {
        return this.tutorialRepository.findById(id)
                .map(tutorialDB -> {
                    tutorialDB.setTitle(tutorial.getTitle());
                    return this.tutorialRepository.save(tutorialDB);
                });
    }

    @Transactional
    public Optional<Boolean> deleteTutorial(Integer id) {
        return this.tutorialRepository.findById(id)
                .map(tutorialDB -> {
                    this.tutorialRepository.deleteById(id);
                    return true;
                });
    }

    @Transactional
    public void deleteAllTutorials() {
        this.tutorialRepository.deleteAll();
    }
}
