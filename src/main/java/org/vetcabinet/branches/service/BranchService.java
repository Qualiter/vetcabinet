package org.vetcabinet.branches.service;

import org.vetcabinet.branches.dto.BranchDto;

import java.util.UUID;

public interface BranchService {
    BranchDto create(BranchDto branch);

    BranchDto update(UUID uuid, BranchDto branch);

    BranchDto get(UUID uuid);

    void delete(UUID uuid);
}
