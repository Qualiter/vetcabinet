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
public class BranchesServiceImplTest {
    private final BranchService branchService;
    private final ClinicService clinicService;
    private final ClinicMapper clinicMapper;
    private final BranchMapper branchMapper;
    private final ClinicRepository clinicRepository;
    private final BranchRepository branchRepository;
    private final ClinicDto clinicDto = new ClinicDto("Clinic code", "Clinic name",
            "Clinic short name", ClinicType.CLINIC);
    private final BranchDto branchDto = new BranchDto("Branch code", null, true,
            "Branch name", "Branch short name", "Branch full name", "Branch address",
            new BigDecimal("34.7657584"), BigDecimal.valueOf(-15.5439843), false, null);

    @AfterEach
    void afterEach() {
        branchRepository.deleteAll();
        clinicRepository.deleteAll();
    }

    @Test
    void create_shouldCreateBranch() {
        ClinicDto createdClinic = clinicService.create(clinicDto);
        branchDto.setClinic(clinicMapper.toClinic(createdClinic));
        BranchDto createdBranch = branchService.create(branchDto);

        assertNotNull(createdBranch);
        assertEquals(branchDto.getCode(), createdBranch.getCode());
        assertEquals(branchDto.getClinic(), createdBranch.getClinic());
        assertEquals(branchDto.getIsMain(), createdBranch.getIsMain());
        assertEquals(branchDto.getName(), createdBranch.getName());
        assertEquals(branchDto.getShortName(), createdBranch.getShortName());
        assertEquals(branchDto.getFullAddress(), createdBranch.getFullAddress());
        assertEquals(branchDto.getAddress(), createdBranch.getAddress());
        assertEquals(branchDto.getLatitude(), createdBranch.getLatitude());
        assertEquals(branchDto.getLongitude(), createdBranch.getLongitude());
        assertEquals(branchDto.getIsStoreyed(), createdBranch.getIsStoreyed());
        assertEquals(branchDto.getFloor(), createdBranch.getFloor());
    }

    @Test
    void create_shouldThrowExceptionIfBranchExists() {
        ClinicDto createdClinic = clinicService.create(clinicDto);
        branchDto.setClinic(clinicMapper.toClinic(createdClinic));
        branchService.create(branchDto);

        assertThrows(AlreadyExistsException.class, () ->
                branchService.create(branchDto));
    }

    @Test
    void update_shouldUpdateBranchData() {
        ClinicDto createdClinic = clinicService.create(clinicDto);
        branchDto.setClinic(clinicMapper.toClinic(createdClinic));
        BranchDto createdBranch = branchService.create(branchDto);
        BranchDto toUpdate = new BranchDto("Branch to update code", clinicMapper.toClinic(createdClinic),
                true, "Branch to update name", "Branch to update short name",
                "Branch to update full name", "Branch to update address",
                new BigDecimal("34.7657584"), BigDecimal.valueOf(-15.5439843), true, 2);
        BranchDto updated = branchService.update(createdBranch.getUuid(), toUpdate);

        assertNotNull(updated);
        assertEquals(toUpdate.getCode(), updated.getCode());
        assertEquals(toUpdate.getClinic().getUuid(), updated.getClinic().getUuid());
        assertEquals(toUpdate.getIsMain(), updated.getIsMain());
        assertEquals(toUpdate.getName(), updated.getName());
        assertEquals(toUpdate.getShortName(), updated.getShortName());
        assertEquals(toUpdate.getFullAddress(), updated.getFullAddress());
        assertEquals(toUpdate.getAddress(), updated.getAddress());
        assertEquals(toUpdate.getLatitude(), updated.getLatitude());
        assertEquals(toUpdate.getLongitude(), updated.getLongitude());
        assertEquals(toUpdate.getIsStoreyed(), updated.getIsStoreyed());
        assertEquals(toUpdate.getFloor(), updated.getFloor());
    }

    @Test
    void update_shouldThrowExceptionIfBranchDataNotExists() {
        assertThrows(NotFoundException.class, () ->
                branchService.update(UUID.randomUUID(), branchDto));
    }

    @Test
    void get_shouldReturnDataForBranch() {
        ClinicDto createdClinic = clinicService.create(clinicDto);
        branchDto.setClinic(clinicMapper.toClinic(createdClinic));
        BranchDto createdBranch = branchService.create(branchDto);
        BranchDto returned = branchService.get(createdBranch.getUuid());

        assertNotNull(returned);
        assertEquals(createdBranch.getCode(), returned.getCode());
        assertEquals(createdBranch.getClinic().getUuid(), returned.getClinic().getUuid());
        assertEquals(createdBranch.getIsMain(), returned.getIsMain());
        assertEquals(createdBranch.getName(), returned.getName());
        assertEquals(createdBranch.getShortName(), returned.getShortName());
        assertEquals(createdBranch.getFullAddress(), returned.getFullAddress());
        assertEquals(createdBranch.getAddress(), returned.getAddress());
        assertEquals(createdBranch.getLatitude(), returned.getLatitude());
        assertEquals(createdBranch.getLongitude(), returned.getLongitude());
        assertEquals(createdBranch.getIsStoreyed(), returned.getIsStoreyed());
        assertEquals(createdBranch.getFloor(), returned.getFloor());
    }

    @Test
    void get_shouldThrowExceptionIfNotFound() {
        assertThrows(NotFoundException.class, () ->
                branchService.get(UUID.randomUUID()));
    }

    @Test
    void delete_shouldDeleteBranchData() {
        ClinicDto createdClinic = clinicService.create(clinicDto);
        branchDto.setClinic(clinicMapper.toClinic(createdClinic));
        BranchDto createdBranch = branchService.create(branchDto);
        branchService.delete(createdBranch.getUuid());

        assertThrows(NotFoundException.class, () ->
                branchService.get(createdBranch.getUuid()));
    }

    @Test
    void delete_shouldThrowExceptionIfBranchNotExists() {
        assertThrows(NotFoundException.class, () ->
                branchService.delete(UUID.randomUUID()));
    }

    @Test
    void getAll_shouldReturnEmptyList() {
        assertTrue(branchService.getAll(0, 10).isEmpty());
    }

    @Test
    void getAll_shouldReturnData() {
        ClinicDto createdClinic = clinicService.create(clinicDto);
        branchDto.setClinic(clinicMapper.toClinic(createdClinic));
        branchService.create(branchDto);
        List<BranchDto> branches = branchService.getAll(0, 10);

        assertEquals(1, branches.size());
    }
}