package com.magadiflo.spring.data.jdbc.app.services;

import com.magadiflo.spring.data.jdbc.app.entities.User;
import com.magadiflo.spring.data.jdbc.app.entities.dto.AuthorityDetails;
import com.magadiflo.spring.data.jdbc.app.repositories.IAuthorityRepository;
import com.magadiflo.spring.data.jdbc.app.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthorityService {

    private final IAuthorityRepository authorityRepository;
    private final IUserRepository userRepository;

    public Optional<AuthorityDetails> getAuthorityDetails(Integer id) {
        return this.authorityRepository.findById(id)
                .map(authorityDB -> {
                    List<User> usersDB = this.userRepository.findAllUsersByAuthority(id);
                    return new AuthorityDetails(authorityDB, usersDB);
                });
    }
}
