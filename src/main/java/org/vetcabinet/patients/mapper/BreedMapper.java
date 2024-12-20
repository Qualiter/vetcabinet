package org.vetcabinet.patients.mapper;

import org.mapstruct.Mapper;
import org.vetcabinet.patients.dto.BreedDto;
import org.vetcabinet.patients.model.Breed;

@Mapper(componentModel = "spring")
public interface BreedMapper {
    Breed toBreed(BreedDto breed);

    BreedDto toBreedDto(Breed breed);
}
