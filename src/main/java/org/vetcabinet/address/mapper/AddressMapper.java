package org.vetcabinet.address.mapper;

import org.mapstruct.Mapper;
import org.vetcabinet.address.dto.RegisterAddressDto;
import org.vetcabinet.address.model.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    RegisterAddressDto toRegisterAddressDto(Address address);

    Address toAddress(RegisterAddressDto registerAddressDto);
}
