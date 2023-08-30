package com.magadiflo.spring.data.jdbc.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@Table(name = "users")
public class User {
    @Id
    private Integer id;
    private String username;
    private String password;
    @Column(value = "account_non_expired")
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Boolean enabled;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private LocalDate birthdate;

    @JsonIgnore
    @MappedCollection(idColumn = "user_id")
    private Set<AuthorityRef> authorities = new HashSet<>();

    public void addAuthority(Authority authority) {
        AuthorityRef authorityRef = new AuthorityRef();
        authorityRef.setAuthorityId(authority.getId());
        this.authorities.add(authorityRef);
    }

    public void removeAuthority(Authority authority) {
        AuthorityRef authorityRef = new AuthorityRef();
        authorityRef.setAuthorityId(authority.getId());
        this.authorities.remove(authorityRef);
    }
}
