package com.magadiflo.spring.data.jdbc.app.repositories;

import com.magadiflo.spring.data.jdbc.app.entities.Tutorial;
import org.springframework.data.repository.ListCrudRepository;

public interface ITutorialRepository extends ListCrudRepository<Tutorial, Integer> {

}
