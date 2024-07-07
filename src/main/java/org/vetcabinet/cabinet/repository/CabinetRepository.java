package org.vetcabinet.cabinet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vetcabinet.branches.model.Branch;
import org.vetcabinet.cabinet.model.Cabinet;

import java.util.UUID;

@Repository
public interface CabinetRepository extends JpaRepository<Cabinet, UUID> {
    boolean existsByNameAndBranch(String name, Branch branch);
}
