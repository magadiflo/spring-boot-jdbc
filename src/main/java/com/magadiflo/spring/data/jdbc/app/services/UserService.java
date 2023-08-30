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
    public Optional<User> getUser(Integer id) {
        return this.userRepository.findById(id);
    }

    @Transactional
    public User createUser(User user) {
        return this.userRepository.save(user);
    }

    @Transactional
    public Optional<User> updateUser(Integer id, User user) {
        return this.userRepository.findById(id)
                .map(userDB -> {
                    userDB.setUsername(user.getUsername());
                    userDB.setPassword(user.getPassword());
                    userDB.setAccountNonExpired(user.getAccountNonExpired());
                    userDB.setAccountNonLocked(user.getAccountNonLocked());
                    userDB.setCredentialsNonExpired(user.getCredentialsNonExpired());
                    userDB.setEnabled(user.getEnabled());
                    userDB.setFirstName(user.getFirstName());
                    userDB.setLastName(user.getLastName());
                    userDB.setEmailAddress(user.getEmailAddress());
                    userDB.setBirthdate(user.getBirthdate());
                    return this.userRepository.save(userDB);
                });
    }

    @Transactional
    public Optional<Boolean> deleteUser(Integer id) {
        return this.userRepository.findById(id)
                .map(userDB -> {
                    this.userRepository.deleteById(id);
                    return true;
                });
    }

    @Transactional
    public Optional<UserDetails> addAuthority(Integer id, Authority authority) {
        return this.userRepository.findById(id)
                .map(userDB -> {
                    userDB.addAuthority(authority);
                    User userSaved = this.userRepository.save(userDB);
                    List<Authority> authorities = this.authorityRepository.findAllAuthoritiesByUserId(userDB.getId());
                    return new UserDetails(userSaved, authorities);
                });
    }

    @Transactional
    public Optional<UserDetails> removeAuthority(Integer id, Authority authority) {
        return this.userRepository.findById(id)
                .map(userDB -> {
                    userDB.removeAuthority(authority);
                    User userSaved = this.userRepository.save(userDB);
                    List<Authority> authorities = this.authorityRepository.findAllAuthoritiesByUserId(userDB.getId());
                    return new UserDetails(userSaved, authorities);
                });
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
