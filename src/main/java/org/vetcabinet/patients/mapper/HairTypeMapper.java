package org.vetcabinet.patients.mapper;

import org.mapstruct.Mapper;
import org.vetcabinet.patients.dto.HairTypeDto;
import org.vetcabinet.patients.model.HairType;

@Mapper(componentModel = "spring")
public interface HairTypeMapper {
    HairType toHairType(HairTypeDto hairType);

    HairTypeDto toColorDto(HairType hairType);
}
