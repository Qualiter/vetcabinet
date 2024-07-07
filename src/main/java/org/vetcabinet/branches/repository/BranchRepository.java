package org.vetcabinet.branches.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vetcabinet.branches.model.Branch;
import org.vetcabinet.clinic.model.Clinic;

import java.util.UUID;

@Repository
public interface BranchRepository extends JpaRepository<Branch, UUID> {
    boolean existsByNameAndClinic(String name, Clinic clinic);
}
