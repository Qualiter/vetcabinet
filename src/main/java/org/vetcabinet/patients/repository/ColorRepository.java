package org.vetcabinet.patients.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vetcabinet.patients.model.Breed;
import org.vetcabinet.patients.model.Color;
import org.vetcabinet.patients.model.Patient;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ColorRepository extends JpaRepository<Color, UUID> {
    Optional<Color> findByName(String name);

}
