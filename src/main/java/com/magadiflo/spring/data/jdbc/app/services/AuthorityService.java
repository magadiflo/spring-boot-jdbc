package com.magadiflo.spring.data.jdbc.app.services;

import com.magadiflo.spring.data.jdbc.app.entities.Authority;
import com.magadiflo.spring.data.jdbc.app.entities.User;
import com.magadiflo.spring.data.jdbc.app.entities.dto.AuthorityDetails;
import com.magadiflo.spring.data.jdbc.app.repositories.IAuthorityRepository;
import com.magadiflo.spring.data.jdbc.app.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthorityService {

    private final IAuthorityRepository authorityRepository;
    private final IUserRepository userRepository;

    @Transactional(readOnly = true)
    public List<Authority> getAuthorities() {
        return this.authorityRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Authority> getAuthority(Integer id) {
        return this.authorityRepository.findById(id);
    }

    @Transactional
    public Authority createAuthority(Authority authority) {
        return this.authorityRepository.save(authority);
    }

    @Transactional
    public Optional<Authority> updateAuthority(Integer id, Authority authority) {
        return this.authorityRepository.findById(id)
                .map(authorityDB -> {
                    authorityDB.setAuthority(authority.getAuthority());
                    return this.authorityRepository.save(authorityDB);
                });
    }

    @Transactional
    public Optional<Boolean> deleteAuthority(Integer id) {
        return this.authorityRepository.findById(id)
                .map(authorityDB -> {
                    this.authorityRepository.deleteById(authorityDB.getId());
                    return true;
                });
    }

    @Transactional(readOnly = true)
    public Optional<AuthorityDetails> getAuthorityDetails(Integer id) {
        return this.authorityRepository.findById(id)
                .map(authorityDB -> {
                    List<User> usersDB = this.userRepository.findAllUsersByAuthority(id);
                    return new AuthorityDetails(authorityDB, usersDB);
                });
    }
}
