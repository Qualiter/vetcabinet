package com.vetcabinet.cabinet.repository;

import com.vetcabinet.cabinet.model.Cabinet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CabinetRepository extends JpaRepository<Cabinet, UUID> {
}
