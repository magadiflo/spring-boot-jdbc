package com.magadiflo.spring.data.jdbc.app.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table(name = "users_authorities")
public class AuthorityRef {
    @Column(value = "authority_id")
    private Integer authorityId;
}
