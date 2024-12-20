package org.vetcabinet.patients.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vetcabinet.patients.model.Patient;

import java.util.UUID;

@Repository
public interface MarkRepository extends JpaRepository<Patient, UUID> {
    
}
