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
import org.vetcabinet.cabinet.dto.CabinetDto;
import org.vetcabinet.cabinet.mapper.CabinetMapper;
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
    private final CabinetMapper cabinetMapper;
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final ClinicRepository clinicRepository;
    private final BranchRepository branchRepository;
    private final CabinetRepository cabinetRepository;
    private final RegisterAddressDto addressDto = new RegisterAddressDto("0000000",
            "Branch full name", new BigDecimal("34.7657584"), BigDecimal.valueOf(-15.5439843),
            true, 2L, "Branch additional data");
    private final ClinicDto clinicDto = new ClinicDto("Clinic code", "Clinic name",
            "Clinic short name", ClinicType.CLINIC);
    private final BranchDto branchDto = new BranchDto(clinicDto, true,
            "Branch name", "Branch short name", addressDto, false, null);
    private final CabinetDto cabinetDto = new CabinetDto(branchDto, 100, "Cabinet name", 1);

    @AfterEach
    void afterEach() {
        cabinetRepository.deleteAll();
        branchRepository.deleteAll();
        clinicRepository.deleteAll();
        addressRepository.deleteAll();
    }

    @Test
    void create_shouldCreateCabinet() {
        Address address = addressRepository.save(addressMapper.toAddress(addressDto));
        branchDto.setAddress(addressMapper.toRegisterAddressDto(address));
        ClinicDto createdClinic = clinicService.create(clinicDto);
        branchDto.setClinic(clinicMapper.toClinicDto(clinicMapper.toClinic(createdClinic)));
        BranchDto createdBranch = branchService.create(branchDto);
        cabinetDto.setBranch(branchMapper.toBranchDto(branchMapper.toBranch(createdBranch)));
        CabinetDto createdCabinet = cabinetService.create(cabinetDto);

        assertNotNull(createdCabinet);
        assertEquals(cabinetDto.getBranch(), createdCabinet.getBranch());
        assertEquals(cabinetDto.getNumber(), createdCabinet.getNumber());
        assertEquals(cabinetDto.getName(), createdCabinet.getName());
        assertEquals(cabinetDto.getFloor(), createdCabinet.getFloor());
    }

    @Test
    void create_shouldThrowExceptionIfCabinetExists() {
        Address address = addressRepository.save(addressMapper.toAddress(addressDto));
        branchDto.setAddress(addressMapper.toRegisterAddressDto(address));
        ClinicDto createdClinic = clinicService.create(clinicDto);
        branchDto.setClinic(clinicMapper.toClinicDto(clinicMapper.toClinic(createdClinic)));
        BranchDto createdBranch = branchService.create(branchDto);
        cabinetDto.setBranch(branchMapper.toBranchDto(branchMapper.toBranch(createdBranch)));
        cabinetService.create(cabinetDto);

        assertThrows(AlreadyExistsException.class, () -> cabinetService.create(cabinetDto));
    }

    @Test
    void update_shouldUpdateCabinetData() {
        Address address = addressRepository.save(addressMapper.toAddress(addressDto));
        branchDto.setAddress(addressMapper.toRegisterAddressDto(address));
        ClinicDto createdClinic = clinicService.create(clinicDto);
        branchDto.setClinic(clinicMapper.toClinicDto(clinicMapper.toClinic(createdClinic)));
        BranchDto createdBranch = branchService.create(branchDto);
        cabinetDto.setBranch(branchMapper.toBranchDto(branchMapper.toBranch(createdBranch)));
        CabinetDto createdCabinet = cabinetService.create(cabinetDto);
        CabinetDto toUpdate = new CabinetDto(branchMapper.toBranchDto(branchMapper.toBranch(createdBranch)),
                54321, "Cabinet to update name", 2);
        CabinetDto updated = cabinetService.update(cabinetMapper.toCabinet(createdCabinet).getUuid(), toUpdate);

        assertNotNull(updated);
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
        Address address = addressRepository.save(addressMapper.toAddress(addressDto));
        branchDto.setAddress(addressMapper.toRegisterAddressDto(address));
        ClinicDto createdClinic = clinicService.create(clinicDto);
        branchDto.setClinic(clinicMapper.toClinicDto(clinicMapper.toClinic(createdClinic)));
        BranchDto createdBranch = branchService.create(branchDto);
        cabinetDto.setBranch(branchMapper.toBranchDto(branchMapper.toBranch(createdBranch)));
        CabinetDto createdCabinet = cabinetService.create(cabinetDto);
        CabinetDto returned = cabinetService.get(cabinetMapper.toCabinet(createdCabinet).getUuid());

        assertNotNull(returned);
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
        Address address = addressRepository.save(addressMapper.toAddress(addressDto));
        branchDto.setAddress(addressMapper.toRegisterAddressDto(address));
        ClinicDto createdClinic = clinicService.create(clinicDto);
        branchDto.setClinic(clinicMapper.toClinicDto(clinicMapper.toClinic(createdClinic)));
        BranchDto createdBranch = branchService.create(branchDto);
        cabinetDto.setBranch(branchMapper.toBranchDto(branchMapper.toBranch(createdBranch)));
        CabinetDto createdCabinet = cabinetService.create(cabinetDto);
        cabinetService.delete(cabinetMapper.toCabinet(createdCabinet).getUuid());

        assertThrows(NotFoundException.class,
                () -> cabinetService.get(cabinetMapper.toCabinet(createdCabinet).getUuid()));
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
        Address address = addressRepository.save(addressMapper.toAddress(addressDto));
        branchDto.setAddress(addressMapper.toRegisterAddressDto(address));
        ClinicDto createdClinic = clinicService.create(clinicDto);
        branchDto.setClinic(clinicMapper.toClinicDto(clinicMapper.toClinic(createdClinic)));
        BranchDto createdBranch = branchService.create(branchDto);
        cabinetDto.setBranch(branchMapper.toBranchDto(branchMapper.toBranch(createdBranch)));
        cabinetService.create(cabinetDto);
        List<CabinetDto> cabinets = cabinetService.getAll(0, 10);

        assertEquals(1, cabinets.size());
    }
}
