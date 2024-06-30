package com.vetcabinet.clinic.service;

import com.vetcabinet.clinic.dto.ClinicDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface ClinicService {
    ClinicDto create(ClinicDto clinic);

    ClinicDto update(UUID uuid, ClinicDto clinic);

    ClinicDto get(UUID uuid);

    void delete(UUID uuid);
}
