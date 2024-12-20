package org.vetcabinet.patients.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vetcabinet.patients.model.HairType;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface HairTypeRepository extends JpaRepository<HairType, UUID> {
    Optional<HairType> findByName(String name);
}
