package org.vetcabinet.patients.mapper;

import org.mapstruct.Mapper;
import org.vetcabinet.patients.dto.ColorDto;
import org.vetcabinet.patients.model.Color;

@Mapper(componentModel = "spring")
public interface ColorMapper {
    Color toColor(ColorDto color);

    ColorDto toColorDto(Color color);
}
