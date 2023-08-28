package com.magadiflo.spring.data.jdbc.app.controllers;

import com.magadiflo.spring.data.jdbc.app.entities.dto.AuthorityDetails;
import com.magadiflo.spring.data.jdbc.app.services.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/authorities")
public class AuthorityController {

    private final AuthorityService authorityService;

    @GetMapping(path = "/{id}/details")
    public ResponseEntity<AuthorityDetails> getAuthorityDetails(@PathVariable Integer id) {
        return this.authorityService.getAuthorityDetails(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
