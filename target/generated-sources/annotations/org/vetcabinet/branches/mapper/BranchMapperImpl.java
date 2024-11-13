package org.vetcabinet.branches.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import org.vetcabinet.branches.dto.BranchDto;
import org.vetcabinet.branches.model.Branch;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-06T20:56:11+0300",
    comments = "version: 1.5.0.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class BranchMapperImpl implements BranchMapper {

    @Override
    public BranchDto toBranchDto(Branch branch) {
        if ( branch == null ) {
            return null;
        }

        BranchDto branchDto = new BranchDto();

        branchDto.setUuid( branch.getUuid() );
        branchDto.setCode( branch.getCode() );
        branchDto.setClinic( branch.getClinic() );
        branchDto.setIsMain( branch.getIsMain() );
        branchDto.setName( branch.getName() );
        branchDto.setShortName( branch.getShortName() );
        branchDto.setFullAddress( branch.getFullAddress() );
        branchDto.setAddress( branch.getAddress() );
        branchDto.setLatitude( branch.getLatitude() );
        branchDto.setLongitude( branch.getLongitude() );
        branchDto.setIsStoreyed( branch.getIsStoreyed() );
        branchDto.setFloor( branch.getFloor() );

        return branchDto;
    }

    @Override
    public Branch toBranch(BranchDto branch) {
        if ( branch == null ) {
            return null;
        }

        Branch branch1 = new Branch();

        branch1.setUuid( branch.getUuid() );
        branch1.setCode( branch.getCode() );
        branch1.setClinic( branch.getClinic() );
        branch1.setIsMain( branch.getIsMain() );
        branch1.setName( branch.getName() );
        branch1.setShortName( branch.getShortName() );
        branch1.setFullAddress( branch.getFullAddress() );
        branch1.setAddress( branch.getAddress() );
        branch1.setLatitude( branch.getLatitude() );
        branch1.setLongitude( branch.getLongitude() );
        branch1.setIsStoreyed( branch.getIsStoreyed() );
        branch1.setFloor( branch.getFloor() );

        return branch1;
    }
}
