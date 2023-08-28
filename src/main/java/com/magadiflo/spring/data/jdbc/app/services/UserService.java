package com.magadiflo.spring.data.jdbc.app.services;

import com.magadiflo.spring.data.jdbc.app.entities.Authority;
import com.magadiflo.spring.data.jdbc.app.entities.User;
import com.magadiflo.spring.data.jdbc.app.entities.dto.UserDetails;
import com.magadiflo.spring.data.jdbc.app.repositories.IAuthorityRepository;
import com.magadiflo.spring.data.jdbc.app.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final IUserRepository userRepository;
    private final IAuthorityRepository authorityRepository;

    @Transactional(readOnly = true)
    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<UserDetails> getUserDetails(Integer id) {
        return this.userRepository.findById(id)
                .map(userDB -> {
                    List<Authority> authoritiesDB = this.authorityRepository.findAllAuthoritiesByUserId(id);
                    return new UserDetails(userDB, authoritiesDB);
                });
    }
}
