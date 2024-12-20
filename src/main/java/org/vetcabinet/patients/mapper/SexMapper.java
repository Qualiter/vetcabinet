package org.vetcabinet.patients.mapper;

import org.mapstruct.Mapper;
import org.vetcabinet.patients.dto.SexDto;
import org.vetcabinet.patients.model.Sex;

@Mapper(componentModel = "spring")
public interface SexMapper {
    Sex toSex(SexDto sex);

    SexDto toSexDto(Sex sex);
}
