package org.vetcabinet.user.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import org.vetcabinet.user.dto.RegisterAddressDto;
import org.vetcabinet.user.model.Address;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-06T20:56:12+0300",
    comments = "version: 1.5.0.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class AddressMapperImpl implements AddressMapper {

    @Override
    public RegisterAddressDto toRegisterAddressDto(Address address) {
        if ( address == null ) {
            return null;
        }

        RegisterAddressDto registerAddressDto = new RegisterAddressDto();

        registerAddressDto.setPostcode( address.getPostcode() );
        registerAddressDto.setFullAddress( address.getFullAddress() );
        registerAddressDto.setLatitude( address.getLatitude() );
        registerAddressDto.setLongitude( address.getLongitude() );
        registerAddressDto.setIsStoreyed( address.getIsStoreyed() );
        registerAddressDto.setFloor( address.getFloor() );
        registerAddressDto.setAdditionalData( address.getAdditionalData() );

        return registerAddressDto;
    }

    @Override
    public Address toAddress(RegisterAddressDto registerAddressDto) {
        if ( registerAddressDto == null ) {
            return null;
        }

        Address address = new Address();

        address.setPostcode( registerAddressDto.getPostcode() );
        address.setFullAddress( registerAddressDto.getFullAddress() );
        address.setLatitude( registerAddressDto.getLatitude() );
        address.setLongitude( registerAddressDto.getLongitude() );
        address.setIsStoreyed( registerAddressDto.getIsStoreyed() );
        address.setFloor( registerAddressDto.getFloor() );
        address.setAdditionalData( registerAddressDto.getAdditionalData() );

        return address;
    }
}
