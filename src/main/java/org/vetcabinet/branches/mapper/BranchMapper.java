package org.vetcabinet.branches.mapper;

import org.mapstruct.Mapper;
import org.vetcabinet.branches.dto.BranchDto;
import org.vetcabinet.branches.model.Branch;

@Mapper(componentModel = "spring")
public interface BranchMapper {
    BranchDto toBranchDto(Branch branch);

    Branch toBranch(BranchDto branch);
}
