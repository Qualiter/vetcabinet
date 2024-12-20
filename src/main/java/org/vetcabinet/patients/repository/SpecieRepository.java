package org.vetcabinet.patients.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vetcabinet.patients.model.Breed;
import org.vetcabinet.patients.model.Patient;
import org.vetcabinet.patients.model.Specie;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpecieRepository extends JpaRepository<Specie, UUID> {
    Optional<Specie> findByName(String name);
}
