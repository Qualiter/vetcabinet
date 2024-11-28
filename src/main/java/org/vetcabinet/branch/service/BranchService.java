package org.vetcabinet.branch.service;

import org.vetcabinet.branch.dto.BranchDto;

import java.util.List;
import java.util.UUID;

public interface BranchService {
    BranchDto create(BranchDto branch);

    BranchDto update(UUID uuid, BranchDto branch);

    BranchDto get(UUID uuid);

    void delete(UUID uuid);

    List<BranchDto> getAll(int offset, int limit);
}
