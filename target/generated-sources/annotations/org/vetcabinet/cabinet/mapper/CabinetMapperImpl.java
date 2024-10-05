package org.vetcabinet.cabinet.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import org.vetcabinet.cabinet.dto.CabinetDto;
import org.vetcabinet.cabinet.model.Cabinet;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-05T17:13:40+0300",
    comments = "version: 1.5.0.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class CabinetMapperImpl implements CabinetMapper {

    @Override
    public CabinetDto toCabinetDto(Cabinet cabinet) {
        if ( cabinet == null ) {
            return null;
        }

        CabinetDto cabinetDto = new CabinetDto();

        cabinetDto.setUuid( cabinet.getUuid() );
        cabinetDto.setBranch( cabinet.getBranch() );
        cabinetDto.setNumber( cabinet.getNumber() );
        cabinetDto.setName( cabinet.getName() );
        cabinetDto.setFloor( cabinet.getFloor() );

        return cabinetDto;
    }

    @Override
    public Cabinet toCabinet(CabinetDto cabinet) {
        if ( cabinet == null ) {
            return null;
        }

        Cabinet cabinet1 = new Cabinet();

        cabinet1.setUuid( cabinet.getUuid() );
        cabinet1.setBranch( cabinet.getBranch() );
        cabinet1.setNumber( cabinet.getNumber() );
        cabinet1.setName( cabinet.getName() );
        cabinet1.setFloor( cabinet.getFloor() );

        return cabinet1;
    }
}
