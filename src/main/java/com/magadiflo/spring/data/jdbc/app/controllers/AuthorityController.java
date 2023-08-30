package com.magadiflo.spring.data.jdbc.app.controllers;

import com.magadiflo.spring.data.jdbc.app.entities.Authority;
import com.magadiflo.spring.data.jdbc.app.entities.dto.AuthorityDetails;
import com.magadiflo.spring.data.jdbc.app.services.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/authorities")
public class AuthorityController {

    private final AuthorityService authorityService;

    @GetMapping
    public ResponseEntity<List<Authority>> getAllAuthorities() {
        return ResponseEntity.ok(this.authorityService.getAuthorities());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Authority> getAuthority(@PathVariable Integer id) {
        return this.authorityService.getAuthority(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Authority> createAuthority(@RequestBody Authority authority) {
        Authority authorityDB = this.authorityService.createAuthority(authority);
        URI uriAuthority = URI.create("/api/v1/authorities" + authorityDB.getId());
        return ResponseEntity.created(uriAuthority).body(authorityDB);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Authority> updateAuthority(@PathVariable Integer id, @RequestBody Authority authority) {
        return this.authorityService.updateAuthority(id, authority)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteAuthority(@PathVariable Integer id) {
        return this.authorityService.deleteAuthority(id)
                .map(wasDeleted -> new ResponseEntity<Void>(HttpStatus.NO_CONTENT))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/{id}/details")
    public ResponseEntity<AuthorityDetails> getAuthorityDetails(@PathVariable Integer id) {
        return this.authorityService.getAuthorityDetails(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
