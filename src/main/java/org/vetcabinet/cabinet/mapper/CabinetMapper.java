package org.vetcabinet.cabinet.mapper;

import org.mapstruct.Mapper;
import org.vetcabinet.cabinet.dto.CabinetDto;
import org.vetcabinet.cabinet.model.Cabinet;

@Mapper(componentModel = "spring")
public interface CabinetMapper {
    CabinetDto toCabinetDto(Cabinet cabinet);

    Cabinet toCabinet(CabinetDto cabinet);
}
