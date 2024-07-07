package org.vetcabinet.clinic.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.vetcabinet.clinic.dto.ClinicDto;
import org.vetcabinet.clinic.repository.ClinicRepository;
import org.vetcabinet.enums.ClinicType;
import org.vetcabinet.exception.AlreadyExistsException;
import org.vetcabinet.exception.NotFoundException;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class ClinicServiceImplTest {
    private final ClinicService clinicService;
    private final ClinicRepository clinicRepository;
    private final ClinicDto clinicDto = new ClinicDto("Clinic code", "Clinic name",
            "Clinic short name", ClinicType.CLINIC);

    @AfterEach
    void afterEach() {
        clinicRepository.deleteAll();
    }

    @Test
    void create_shouldCreateClinic() {
        ClinicDto created = clinicService.create(clinicDto);

        assertNotNull(created);
        assertEquals(clinicDto.getCode(), created.getCode());
        assertEquals(clinicDto.getName(), created.getName());
        assertEquals(clinicDto.getShortName(), created.getShortName());
        assertEquals(clinicDto.getType(), created.getType());
    }

    @Test
    void create_shouldThrowExceptionIfClinicExists() {
        clinicService.create(clinicDto);

        assertThrows(AlreadyExistsException.class, () ->
                clinicService.create(clinicDto));
    }

    @Test
    void update_shouldUpdateClinicData() {
        ClinicDto created = clinicService.create(clinicDto);
        ClinicDto toUpdate = new ClinicDto("Clinic code to update", "Clinic name to update",
                "Clinic short name to update", ClinicType.CLINIC);
        ClinicDto updated = clinicService.update(created.getUuid(), toUpdate);

        assertNotNull(updated);
        assertEquals(toUpdate.getCode(), updated.getCode());
        assertEquals(toUpdate.getName(), updated.getName());
        assertEquals(toUpdate.getShortName(), updated.getShortName());
        assertEquals(toUpdate.getType(), updated.getType());
    }

    @Test
    void update_shouldThrowExceptionIfClinicDataNotExists() {
        assertThrows(NotFoundException.class, () ->
                clinicService.update(UUID.randomUUID(), clinicDto));
    }

    @Test
    void get_shouldReturnDataForClinic() {
        ClinicDto created = clinicService.create(clinicDto);
        ClinicDto returned = clinicService.get(created.getUuid());

        assertNotNull(returned);
        assertEquals(created.getUuid(), returned.getUuid());
        assertEquals(created.getCode(), returned.getCode());
        assertEquals(created.getName(), returned.getName());
        assertEquals(created.getShortName(), returned.getShortName());
        assertEquals(created.getType(), returned.getType());
    }

    @Test
    void get_shouldThrowExceptionIfNotFound() {
        assertThrows(NotFoundException.class, () ->
                clinicService.get(UUID.randomUUID()));
    }

    @Test
    void delete_shouldDeleteClinicData() {
        ClinicDto created = clinicService.create(clinicDto);
        clinicService.delete(created.getUuid());

        assertThrows(NotFoundException.class, () ->
                clinicService.get(created.getUuid()));
    }

    @Test
    void delete_shouldThrowExceptionIfClinicNotExists() {
        assertThrows(NotFoundException.class, () ->
                clinicService.get(UUID.randomUUID()));
    }
}