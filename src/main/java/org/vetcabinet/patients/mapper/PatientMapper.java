package org.vetcabinet.patients.mapper;

import org.mapstruct.Mapper;
import org.vetcabinet.patients.dto.PatientDto;
import org.vetcabinet.patients.dto.PatientUpdateDto;
import org.vetcabinet.patients.model.Patient;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    Patient toPatient(PatientDto patients);
    Patient toPatient(PatientUpdateDto patients);

    PatientDto toPatientDto(Patient patient);

    PatientUpdateDto toPatientUpdateDto(Patient patient);
}
