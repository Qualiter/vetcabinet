package org.vetcabinet.user.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import org.vetcabinet.user.dto.RegisterAddressDto;
import org.vetcabinet.user.dto.RegisterUserDto;
import org.vetcabinet.user.model.Address;
import org.vetcabinet.user.model.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-13T22:42:42+0300",
    comments = "version: 1.5.0.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public RegisterUserDto toRegisterUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        RegisterUserDto registerUserDto = new RegisterUserDto();

        registerUserDto.setName( user.getName() );
        registerUserDto.setSurname( user.getSurname() );
        registerUserDto.setLogin( user.getLogin() );
        registerUserDto.setPassword( user.getPassword() );
        registerUserDto.setPhone( user.getPhone() );
        String[] additionalPhones = user.getAdditionalPhones();
        if ( additionalPhones != null ) {
            registerUserDto.setAdditionalPhones( Arrays.copyOf( additionalPhones, additionalPhones.length ) );
        }
        registerUserDto.setEmail( user.getEmail() );
        registerUserDto.setPatronymic( user.getPatronymic() );
        registerUserDto.setBirthday( user.getBirthday() );
        registerUserDto.setAddresses( addressListToRegisterAddressDtoList( user.getAddresses() ) );

        return registerUserDto;
    }

    @Override
    public User toUser(RegisterUserDto registerUserDTO) {
        if ( registerUserDTO == null ) {
            return null;
        }

        User user = new User();

        user.setLogin( registerUserDTO.getLogin() );
        user.setPassword( registerUserDTO.getPassword() );
        user.setName( registerUserDTO.getName() );
        user.setSurname( registerUserDTO.getSurname() );
        user.setPatronymic( registerUserDTO.getPatronymic() );
        user.setPhone( registerUserDTO.getPhone() );
        String[] additionalPhones = registerUserDTO.getAdditionalPhones();
        if ( additionalPhones != null ) {
            user.setAdditionalPhones( Arrays.copyOf( additionalPhones, additionalPhones.length ) );
        }
        user.setEmail( registerUserDTO.getEmail() );
        user.setBirthday( registerUserDTO.getBirthday() );
        user.setAddresses( registerAddressDtoListToAddressList( registerUserDTO.getAddresses() ) );

        return user;
    }

    protected RegisterAddressDto addressToRegisterAddressDto(Address address) {
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

    protected List<RegisterAddressDto> addressListToRegisterAddressDtoList(List<Address> list) {
        if ( list == null ) {
            return null;
        }

        List<RegisterAddressDto> list1 = new ArrayList<RegisterAddressDto>( list.size() );
        for ( Address address : list ) {
            list1.add( addressToRegisterAddressDto( address ) );
        }

        return list1;
    }

    protected Address registerAddressDtoToAddress(RegisterAddressDto registerAddressDto) {
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

    protected List<Address> registerAddressDtoListToAddressList(List<RegisterAddressDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Address> list1 = new ArrayList<Address>( list.size() );
        for ( RegisterAddressDto registerAddressDto : list ) {
            list1.add( registerAddressDtoToAddress( registerAddressDto ) );
        }

        return list1;
    }
}
