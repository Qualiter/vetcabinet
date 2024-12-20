package org.vetcabinet.patients.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vetcabinet.patients.model.Sex;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SexRepository extends JpaRepository<Sex, UUID> {
    Optional<Sex> findByName(String name);
}
