package com.vetcabinet.clinic.service;

import com.vetcabinet.clinic.dto.ClinicDto;
import com.vetcabinet.clinic.repository.ClinicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClinicServiceImpl implements ClinicService {
    private final ClinicRepository clinicRepository;

    @Override
    public ClinicDto create(ClinicDto clinic) {
        return null;
    }

    @Override
    public ClinicDto update(UUID uuid, ClinicDto clinic) {
        return null;
    }

    @Override
    public ClinicDto get(UUID uuid) {
        return null;
    }

    @Override
    public void delete(UUID uuid) {

    }
}
