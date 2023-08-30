package com.magadiflo.spring.data.jdbc.app.services;

import com.magadiflo.spring.data.jdbc.app.entities.Owner;
import com.magadiflo.spring.data.jdbc.app.repositories.IOwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OwnerService {
    private final IOwnerRepository ownerRepository;

    @Transactional(readOnly = true)
    public List<Owner> getAllOwners() {
        return this.ownerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Owner> getOwner(Integer id) {
        return this.ownerRepository.findById(id);
    }

    @Transactional
    public Owner createOwner(Owner owner) {
        return this.ownerRepository.save(owner);
    }

    @Transactional
    public List<Owner> createListOwners(List<Owner> owner) {
        return this.ownerRepository.saveAll(owner);
    }

    @Transactional
    public Optional<Owner> updateOwner(Integer id, Owner owner) {
        return this.ownerRepository.findById(id)
                .map(ownerDB -> {
                    ownerDB.setFullName(owner.getFullName());
                    ownerDB.setEmail(owner.getEmail());
                    ownerDB.setUsername(owner.getUsername());
                    ownerDB.setAddress(owner.getAddress());
                    return this.ownerRepository.save(ownerDB);
                });
    }

    @Transactional
    public Optional<Owner> patchOwner(Integer id, Owner owner) {
        return this.ownerRepository.findById(id)
                .map(ownerDB -> {
                    ownerDB.setEmail(owner.getEmail());
                    return this.ownerRepository.save(ownerDB);
                });
    }

    @Transactional
    public Optional<Boolean> deleteOwner(Integer id) {
        return this.ownerRepository.findById(id)
                .map(ownerDB -> {
                    this.ownerRepository.deleteById(id);
                    return true;
                });
    }

    @Transactional
    public void deleteAllOwners() {
        this.ownerRepository.deleteAll();
    }
}
