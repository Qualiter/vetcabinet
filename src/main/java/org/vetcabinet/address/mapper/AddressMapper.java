package org.vetcabinet.address.mapper;

import org.mapstruct.Mapper;
import org.vetcabinet.address.dto.RegisterAddressDto;
import org.vetcabinet.address.model.Address;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    RegisterAddressDto toRegisterAddressDto(Address address);

    Address toAddress(RegisterAddressDto registerAddressDto);

    List<RegisterAddressDto> toListRegisterAddressDto(List<Address> address);

    List<Address> toListAddress(List<RegisterAddressDto> registerAddressDto);
}
