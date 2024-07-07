package org.vetcabinet.clinic.service;

import org.vetcabinet.clinic.dto.ClinicDto;

import java.util.UUID;

public interface ClinicService {
    ClinicDto create(ClinicDto clinic);

    ClinicDto update(UUID uuid, ClinicDto clinic);

    ClinicDto get(UUID uuid);

    void delete(UUID uuid);
}
