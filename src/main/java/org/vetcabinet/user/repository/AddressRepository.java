package org.vetcabinet.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vetcabinet.user.model.Address;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}