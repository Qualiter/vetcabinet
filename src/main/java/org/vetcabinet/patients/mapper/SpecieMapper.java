package org.vetcabinet.patients.mapper;

import org.mapstruct.Mapper;
import org.vetcabinet.patients.dto.SpecieDto;
import org.vetcabinet.patients.model.Specie;

@Mapper(componentModel = "spring")
public interface SpecieMapper {
    Specie toSpecie(SpecieDto specie);

    SpecieDto toSexDto(Specie specie);
}
