package com.magadiflo.spring.data.jdbc.app.repositories;

import com.magadiflo.spring.data.jdbc.app.entities.Owner;
import org.springframework.data.repository.ListCrudRepository;

public interface IOwnerRepository extends ListCrudRepository<Owner, Integer> {
}
