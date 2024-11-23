package org.vetcabinet.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vetcabinet.user.model.Phone;

import java.util.UUID;

public interface PhoneRepository extends JpaRepository<Phone, UUID> {
}
