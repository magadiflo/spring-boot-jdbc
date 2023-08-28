package com.magadiflo.spring.data.jdbc.app.repositories;

import com.magadiflo.spring.data.jdbc.app.entities.Authority;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAuthorityRepository extends ListCrudRepository<Authority, Integer> {
    @Query(value = """
            SELECT a.*
            FROM authorities AS a
                JOIN users_authorities AS ua ON(a.id = ua.authority_id)
            WHERE ua.user_id = :id
            """)
    List<Authority> findAllAuthoritiesByUserId(@Param("id") Integer id);
}
