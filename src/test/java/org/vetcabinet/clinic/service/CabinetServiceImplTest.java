package org.vetcabinet.clinic.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.vetcabinet.branches.dto.BranchDto;
import org.vetcabinet.branches.mapper.BranchMapper;
import org.vetcabinet.branches.repository.BranchRepository;
import org.vetcabinet.branches.service.BranchService;
import org.vetcabinet.cabinet.dto.CabinetDto;
import org.vetcabinet.cabinet.repository.CabinetRepository;
import org.vetcabinet.cabinet.service.CabinetService;
import org.vetcabinet.clinic.dto.ClinicDto;
import org.vetcabinet.clinic.mapper.ClinicMapper;
import org.vetcabinet.clinic.model.ClinicType;
import org.vetcabinet.clinic.repository.ClinicRepository;
import org.vetcabinet.exception.AlreadyExistsException;
import org.vetcabinet.exception.NotFoundException;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CabinetServiceImplTest {
    private final CabinetService cabinetService;
    private final BranchService branchService;
    private final ClinicService clinicService;
    private final ClinicMapper clinicMapper;
    private final BranchMapper branchMapper;
    private final ClinicRepository clinicRepository;
    private final BranchRepository branchRepository;
    private final CabinetRepository cabinetRepository;
    private final CabinetDto cabinetDto = new CabinetDto(null, 123L, "Cabinet name", 2);
    private final ClinicDto clinicDto = new ClinicDto("Clinic code", "Clinic name", "Clinic short name",
            ClinicType.CLINIC);
    private final BranchDto branchDto = new BranchDto("Branch code", null, true, "Branch name",
            "Branch short name", "Branch full name", "Branch address", new BigDecimal("34.7657584"),
            BigDecimal.valueOf(-15.5439843), false, null);

    @AfterEach
    void afterEach() {
        cabinetRepository.deleteAll();
        branchRepository.deleteAll();
        clinicRepository.deleteAll();
    }

    @Test
    void create_shouldCreateCabinet() {
        ClinicDto createdClinic = clinicService.create(clinicDto);
        branchDto.setClinic(clinicMapper.toClinic(createdClinic));
        BranchDto createdBranch = branchService.create(branchDto);
        cabinetDto.setBranch(branchMapper.toBranch(createdBranch));
        CabinetDto createdCabinet = cabinetService.create(cabinetDto);

        assertNotNull(createdCabinet);
        assertEquals(cabinetDto.getBranch(), createdCabinet.getBranch());
        assertEquals(cabinetDto.getNumber(), createdCabinet.getNumber());
        assertEquals(cabinetDto.getName(), createdCabinet.getName());
        assertEquals(cabinetDto.getFloor(), createdCabinet.getFloor());
    }

    @Test
    void create_shouldThrowExceptionIfCabinetExists() {
        ClinicDto createdClinic = clinicService.create(clinicDto);
        branchDto.setClinic(clinicMapper.toClinic(createdClinic));
        BranchDto createdBranch = branchService.create(branchDto);
        cabinetDto.setBranch(branchMapper.toBranch(createdBranch));
        cabinetService.create(cabinetDto);

        assertThrows(AlreadyExistsException.class, () -> cabinetService.create(cabinetDto));
    }

    @Test
    void update_shouldUpdateCabinetData() {
        ClinicDto createdClinic = clinicService.create(clinicDto);
        branchDto.setClinic(clinicMapper.toClinic(createdClinic));
        BranchDto createdBranch = branchService.create(branchDto);
        cabinetDto.setBranch(branchMapper.toBranch(createdBranch));
        CabinetDto createdCabinet = cabinetService.create(cabinetDto);
        CabinetDto toUpdate = new CabinetDto(branchMapper.toBranch(createdBranch), 54321L, "Cabinet to update name", 2);
        CabinetDto updated = cabinetService.update(createdCabinet.getUuid(), toUpdate);

        assertNotNull(updated);
        assertEquals(toUpdate.getBranch().getUuid(), updated.getBranch().getUuid());
        assertEquals(toUpdate.getNumber(), updated.getNumber());
        assertEquals(toUpdate.getName(), updated.getName());
        assertEquals(toUpdate.getFloor(), updated.getFloor());
    }

    @Test
    void update_shouldThrowExceptionIfCabinetDataNotExists() {
        assertThrows(NotFoundException.class, () -> cabinetService.update(UUID.randomUUID(), cabinetDto));
    }

    @Test
    void get_shouldReturnDataForCabinet() {
        ClinicDto createdClinic = clinicService.create(clinicDto);
        branchDto.setClinic(clinicMapper.toClinic(createdClinic));
        BranchDto createdBranch = branchService.create(branchDto);
        cabinetDto.setBranch(branchMapper.toBranch(createdBranch));
        CabinetDto createdCabinet = cabinetService.create(cabinetDto);
        CabinetDto returned = cabinetService.get(createdCabinet.getUuid());

        assertNotNull(returned);
        assertEquals(createdCabinet.getBranch().getUuid(), returned.getBranch().getUuid());
        assertEquals(createdCabinet.getNumber(), returned.getNumber());
        assertEquals(createdCabinet.getName(), returned.getName());
        assertEquals(createdCabinet.getFloor(), returned.getFloor());
    }

    @Test
    void get_shouldThrowExceptionIfNotFound() {
        assertThrows(NotFoundException.class, () -> cabinetService.get(UUID.randomUUID()));
    }

    @Test
    void delete_shouldDeleteCabinetData() {
        ClinicDto createdClinic = clinicService.create(clinicDto);
        branchDto.setClinic(clinicMapper.toClinic(createdClinic));
        BranchDto createdBranch = branchService.create(branchDto);
        cabinetDto.setBranch(branchMapper.toBranch(createdBranch));
        CabinetDto createdCabinet = cabinetService.create(cabinetDto);
        cabinetService.delete(createdCabinet.getUuid());

        assertThrows(NotFoundException.class, () -> cabinetService.get(createdCabinet.getUuid()));
    }

    @Test
    void delete_shouldThrowExceptionIfCabinetNotExists() {
        assertThrows(NotFoundException.class, () -> cabinetService.delete(UUID.randomUUID()));
    }

    @Test
    void getAll_shouldReturnEmptyList() {
        assertTrue(cabinetService.getAll(0, 10).isEmpty());
    }

    @Test
    void getAll_shouldReturnData() {
        ClinicDto createdClinic = clinicService.create(clinicDto);
        branchDto.setClinic(clinicMapper.toClinic(createdClinic));
        BranchDto createdBranch = branchService.create(branchDto);
        cabinetDto.setBranch(branchMapper.toBranch(createdBranch));
        cabinetService.create(cabinetDto);
        List<CabinetDto> cabinets = cabinetService.getAll(0, 10);

        assertEquals(1, cabinets.size());
    }
}
