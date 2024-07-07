package org.vetcabinet.clinic.mapper;

import org.mapstruct.Mapper;
import org.vetcabinet.clinic.dto.ClinicDto;
import org.vetcabinet.clinic.model.Clinic;

@Mapper(componentModel = "spring")
public interface ClinicMapper {
    Clinic toClinic(ClinicDto clinic);

    ClinicDto toClinicDto(Clinic clinic);
}
