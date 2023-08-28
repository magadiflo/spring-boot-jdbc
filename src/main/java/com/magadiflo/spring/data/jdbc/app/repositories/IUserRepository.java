package com.magadiflo.spring.data.jdbc.app.repositories;

import com.magadiflo.spring.data.jdbc.app.entities.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUserRepository extends ListCrudRepository<User, Integer>,
        ListPagingAndSortingRepository<User, Integer> {
    @Query(value = """
            SELECT u.*
            FROM users AS u
                JOIN users_authorities AS ua ON(u.id = ua.user_id)
            WHERE ua.authority_id = :id
            """)
    List<User> findAllUsersByAuthority(@Param("id") Integer id);
}
