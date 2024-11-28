package org.vetcabinet.clinic.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.vetcabinet.address.dto.RegisterAddressDto;
import org.vetcabinet.address.mapper.AddressMapper;
import org.vetcabinet.address.model.Address;
import org.vetcabinet.address.repository.AddressRepository;
import org.vetcabinet.branch.dto.BranchDto;
import org.vetcabinet.branch.mapper.BranchMapper;
import org.vetcabinet.branch.repository.BranchRepository;
import org.vetcabinet.branch.service.BranchService;
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
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final ClinicRepository clinicRepository;
    private final BranchRepository branchRepository;
    private final RegisterAddressDto addressDto = new RegisterAddressDto("0000000",
            "Branch full name", new BigDecimal("34.7657584"), BigDecimal.valueOf(-15.5439843),
            true, 2L, "Branch additional data");
    private final ClinicDto clinicDto = new ClinicDto("Clinic code", "Clinic name",
            "Clinic short name", ClinicType.CLINIC);
    private final BranchDto branchDto = new BranchDto(clinicDto, true,
            "Branch name", "Branch short name", null, false, null);

    @AfterEach
    void afterEach() {
        branchRepository.deleteAll();
        clinicRepository.deleteAll();
        addressRepository.deleteAll();
    }

    @Test
    void create_shouldCreateBranch() {
        Address address = addressRepository.save(addressMapper.toAddress(addressDto));
        branchDto.setAddress(addressMapper.toRegisterAddressDto(address));
        ClinicDto createdClinic = clinicService.create(clinicDto);
        branchDto.setClinic(clinicMapper.toClinicDto(clinicMapper.toClinic(createdClinic)));
        BranchDto createdBranch = branchService.create(branchDto);

        assertNotNull(createdBranch);
        assertEquals(branchDto.getClinic(), createdBranch.getClinic());
        assertEquals(branchDto.getIsMain(), createdBranch.getIsMain());
        assertEquals(branchDto.getName(), createdBranch.getName());
        assertEquals(branchDto.getShortName(), createdBranch.getShortName());
        assertEquals(branchDto.getAddress(), createdBranch.getAddress());
        assertEquals(branchDto.getIsStoreyed(), createdBranch.getIsStoreyed());
        assertEquals(branchDto.getFloor(), createdBranch.getFloor());
    }

    @Test
    void create_shouldThrowExceptionIfBranchExists() {
        Address address = addressRepository.save(addressMapper.toAddress(addressDto));
        branchDto.setAddress(addressMapper.toRegisterAddressDto(address));
        ClinicDto createdClinic = clinicService.create(clinicDto);
        branchDto.setClinic(clinicMapper.toClinicDto(clinicMapper.toClinic(createdClinic)));
        branchService.create(branchDto);

        assertThrows(AlreadyExistsException.class, () ->
                branchService.create(branchDto));
    }

    @Test
    void update_shouldUpdateBranchData() {
        Address address = addressRepository.save(addressMapper.toAddress(addressDto));
        branchDto.setAddress(addressMapper.toRegisterAddressDto(address));
        ClinicDto createdClinic = clinicService.create(clinicDto);
        branchDto.setClinic(clinicMapper.toClinicDto(clinicMapper.toClinic(createdClinic)));
        BranchDto createdBranch = branchService.create(branchDto);
        BranchDto toUpdate = new BranchDto(clinicDto, true, "Branch new name",
                "Branch new short name", addressDto, false, null);
        BranchDto updated = branchService.update(branchMapper.toBranch(createdBranch).getUuid(), toUpdate);

        assertNotNull(updated);
        assertEquals(toUpdate.getIsMain(), updated.getIsMain());
        assertEquals(toUpdate.getName(), updated.getName());
        assertEquals(toUpdate.getShortName(), updated.getShortName());
        assertEquals(toUpdate.getAddress(), updated.getAddress());
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
        Address address = addressRepository.save(addressMapper.toAddress(addressDto));
        branchDto.setAddress(addressMapper.toRegisterAddressDto(address));
        ClinicDto createdClinic = clinicService.create(clinicDto);
        branchDto.setClinic(clinicMapper.toClinicDto(clinicMapper.toClinic(createdClinic)));
        BranchDto createdBranch = branchService.create(branchDto);
        BranchDto returned = branchService.get(branchMapper.toBranch(createdBranch).getUuid());

        assertNotNull(returned);
        assertEquals(createdBranch.getIsMain(), returned.getIsMain());
        assertEquals(createdBranch.getName(), returned.getName());
        assertEquals(createdBranch.getShortName(), returned.getShortName());
        assertEquals(createdBranch.getAddress(), returned.getAddress());
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
        Address address = addressRepository.save(addressMapper.toAddress(addressDto));
        branchDto.setAddress(addressMapper.toRegisterAddressDto(address));
        ClinicDto createdClinic = clinicService.create(clinicDto);
        branchDto.setClinic(clinicMapper.toClinicDto(clinicMapper.toClinic(createdClinic)));
        BranchDto createdBranch = branchService.create(branchDto);
        branchService.delete(branchMapper.toBranch(createdBranch).getUuid());

        assertThrows(NotFoundException.class, () ->
                branchService.get(branchMapper.toBranch(createdBranch).getUuid()));
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
        Address address = addressRepository.save(addressMapper.toAddress(addressDto));
        branchDto.setAddress(addressMapper.toRegisterAddressDto(address));
        ClinicDto createdClinic = clinicService.create(clinicDto);
        branchDto.setClinic(clinicMapper.toClinicDto(clinicMapper.toClinic(createdClinic)));
        branchService.create(branchDto);
        List<BranchDto> branches = branchService.getAll(0, 10);

        assertEquals(1, branches.size());
    }
}