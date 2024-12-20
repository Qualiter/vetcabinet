package org.vetcabinet.user.mapper;

import org.mapstruct.Mapper;
import org.vetcabinet.user.dto.PhoneDto;
import org.vetcabinet.user.model.Phone;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PhoneMapper {
    Phone toPhone(PhoneDto phone);

    PhoneDto toPhoneDto(Phone phone);

    List<Phone> toListPhone(List<PhoneDto> phone);

    List<PhoneDto> toListPhoneDto(List<Phone> phone);
}