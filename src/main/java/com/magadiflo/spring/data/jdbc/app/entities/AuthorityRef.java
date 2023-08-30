package com.magadiflo.spring.data.jdbc.app.entities;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(name = "users_authorities")
public class AuthorityRef {
    @Column(value = "authority_id")
    private Integer authorityId;
}
