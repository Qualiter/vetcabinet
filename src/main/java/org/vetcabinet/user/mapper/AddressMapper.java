package org.vetcabinet.user.mapper;

import org.mapstruct.Mapper;
import org.vetcabinet.user.dto.RegisterAddressDto;
import org.vetcabinet.user.model.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    RegisterAddressDto toRegisterAddressDto(Address address);

    Address toAddress(RegisterAddressDto registerAddressDto);
}
