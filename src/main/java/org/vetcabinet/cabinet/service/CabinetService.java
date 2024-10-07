package org.vetcabinet.cabinet.service;

import org.vetcabinet.cabinet.dto.CabinetDto;

import java.util.List;
import java.util.UUID;

public interface CabinetService {
    CabinetDto create(CabinetDto cabinet);

    CabinetDto update(UUID uuid, CabinetDto cabinet);

    CabinetDto get(UUID uuid);

    void delete(UUID uuid);

    List<CabinetDto> getAll(int offset, int limit);
}
