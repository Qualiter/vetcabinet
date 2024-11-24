package org.vetcabinet.address.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vetcabinet.address.model.Address;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}