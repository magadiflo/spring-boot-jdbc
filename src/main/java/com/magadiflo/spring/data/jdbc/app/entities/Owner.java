package com.magadiflo.spring.data.jdbc.app.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table(name = "owners")
public class Owner {
    @Id
    private Integer id;
    private String fullName;
    private String email;
    private String username;

    @MappedCollection(idColumn = "owner_id")
    private Address address;
}
