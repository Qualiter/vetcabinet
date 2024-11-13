package org.vetcabinet.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vetcabinet.user.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}