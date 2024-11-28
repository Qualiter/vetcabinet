package org.vetcabinet.branch.mapper;

import org.mapstruct.Mapper;
import org.vetcabinet.branch.dto.BranchDto;
import org.vetcabinet.branch.model.Branch;

@Mapper(componentModel = "spring")
public interface BranchMapper {
    BranchDto toBranchDto(Branch branch);

    Branch toBranch(BranchDto branch);
}