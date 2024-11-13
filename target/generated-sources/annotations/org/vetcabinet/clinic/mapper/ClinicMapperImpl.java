package org.vetcabinet.clinic.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import org.vetcabinet.clinic.dto.ClinicDto;
import org.vetcabinet.clinic.model.Clinic;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-06T20:56:11+0300",
    comments = "version: 1.5.0.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class ClinicMapperImpl implements ClinicMapper {

    @Override
    public Clinic toClinic(ClinicDto clinic) {
        if ( clinic == null ) {
            return null;
        }

        Clinic clinic1 = new Clinic();

        clinic1.setUuid( clinic.getUuid() );
        clinic1.setCode( clinic.getCode() );
        clinic1.setName( clinic.getName() );
        clinic1.setShortName( clinic.getShortName() );
        clinic1.setType( clinic.getType() );

        return clinic1;
    }

    @Override
    public ClinicDto toClinicDto(Clinic clinic) {
        if ( clinic == null ) {
            return null;
        }

        ClinicDto clinicDto = new ClinicDto();

        clinicDto.setUuid( clinic.getUuid() );
        clinicDto.setCode( clinic.getCode() );
        clinicDto.setName( clinic.getName() );
        clinicDto.setShortName( clinic.getShortName() );
        clinicDto.setType( clinic.getType() );

        return clinicDto;
    }
}
