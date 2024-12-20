package org.vetcabinet.patients.mapper;

import org.mapstruct.Mapper;
import org.vetcabinet.patients.dto.MarkDto;
import org.vetcabinet.patients.model.Mark;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MarkMapper {
    Mark toMark(MarkDto specie);

    MarkDto toMarkDto(Mark specie);

    List<Mark> toListMark(List<MarkDto> specie);

    List<MarkDto> toListMarkDto(List<Mark> specie);
}
