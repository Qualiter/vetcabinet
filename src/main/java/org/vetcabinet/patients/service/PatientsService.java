package org.vetcabinet.patients.service;

import org.vetcabinet.patients.dto.PatientDto;
import org.vetcabinet.patients.dto.PatientUpdateDto;

import java.util.UUID;

public interface PatientsService {
    PatientDto create(PatientDto patient);

    PatientUpdateDto update(PatientUpdateDto patient, UUID id);
}
