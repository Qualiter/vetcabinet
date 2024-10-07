package org.vetcabinet.clinic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.vetcabinet.clinic.dto.ClinicDto;
import org.vetcabinet.clinic.mapper.ClinicMapper;
import org.vetcabinet.clinic.model.Clinic;
import org.vetcabinet.clinic.repository.ClinicRepository;
import org.vetcabinet.exception.AlreadyExistsException;
import org.vetcabinet.exception.NotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClinicServiceImpl implements ClinicService {
    private final ClinicRepository clinicRepository;
    private final ClinicMapper clinicMapper;

    @Override
    public ClinicDto create(ClinicDto clinic) {
        Clinic clinicData = clinicMapper.toClinic(clinic);
        if (clinicRepository.existsByCodeAndName(clinicData.getCode(), clinicData.getName())) {
            throw new AlreadyExistsException("Данные о клинике уже есть в системе");
        }
        Clinic saved = clinicRepository.save(clinicData);
        return clinicMapper.toClinicDto(saved);
    }

    @Override
    public ClinicDto update(UUID uuid, ClinicDto clinic) {
        Clinic clinicData = clinicRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("Данные о клинике не найдены"));
        Clinic updated = clinicMapper.toClinic(clinic);
        updated.setUuid(clinicData.getUuid());
        Clinic saved = clinicRepository.save(updated);
        return clinicMapper.toClinicDto(saved);
    }

    @Override
    public ClinicDto get(UUID uuid) {
        Clinic clinicData = clinicRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("Данные о клинике не найдены"));
        return clinicMapper.toClinicDto(clinicData);
    }

    @Override
    public void delete(UUID uuid) {
        Clinic clinicData = clinicRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("Данные о клинике не найдены"));
        clinicRepository.delete(clinicData);
    }

    @Override
    public List<ClinicDto> getAll(int offset, int limit) {
        return clinicRepository.findAll(PageRequest.of(offset, limit)).stream()
                .sorted(Comparator.comparing(Clinic::getType))
                .map(clinicMapper::toClinicDto).toList();
    }
}