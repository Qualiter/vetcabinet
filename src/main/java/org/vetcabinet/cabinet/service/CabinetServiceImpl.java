package org.vetcabinet.cabinet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vetcabinet.cabinet.dto.CabinetDto;
import org.vetcabinet.cabinet.mapper.CabinetMapper;
import org.vetcabinet.cabinet.model.Cabinet;
import org.vetcabinet.cabinet.repository.CabinetRepository;
import org.vetcabinet.exception.AlreadyExistsException;
import org.vetcabinet.exception.NotFoundException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CabinetServiceImpl implements CabinetService {
    private final CabinetRepository cabinetRepository;
    private final CabinetMapper cabinetMapper;

    @Override
    public CabinetDto create(CabinetDto cabinet) {
        Cabinet cabinetData = cabinetMapper.toCabinet(cabinet);
        if (cabinetRepository.existsByNameAndBranch(cabinetData.getName(), cabinetData.getBranch())) {
            throw new AlreadyExistsException("Данные уже есть в системе");
        }
        Cabinet saved = cabinetRepository.save(cabinetData);
        return cabinetMapper.toCabinetDto(saved);
    }

    @Override
    public CabinetDto update(UUID uuid, CabinetDto cabinet) {
        Cabinet cabinetData = cabinetRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("Данные о кабинете не найдены"));
        Cabinet updated = cabinetMapper.toCabinet(cabinet);
        updated.setUuid(cabinetData.getUuid());
        Cabinet saved = cabinetRepository.save(updated);
        return cabinetMapper.toCabinetDto(saved);
    }

    @Override
    public CabinetDto get(UUID uuid) {
        Cabinet cabinetData = cabinetRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("Данные о кабинете не найдены"));
        return cabinetMapper.toCabinetDto(cabinetData);
    }

    @Override
    public void delete(UUID uuid) {
        Cabinet cabinetData = cabinetRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("Данные о кабинете не найдены"));
        cabinetRepository.delete(cabinetData);
    }
}
