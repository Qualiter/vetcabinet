package org.vetcabinet.branches.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.vetcabinet.branches.dto.BranchDto;
import org.vetcabinet.branches.mapper.BranchMapper;
import org.vetcabinet.branches.model.Branch;
import org.vetcabinet.branches.repository.BranchRepository;
import org.vetcabinet.exception.AlreadyExistsException;
import org.vetcabinet.exception.NotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {
    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;

    @Override
    public BranchDto create(BranchDto branch) {
        Branch branchData = branchMapper.toBranch(branch);
        if (branchRepository.existsByNameAndClinic(branchData.getName(), branchData.getClinic())) {
            throw new AlreadyExistsException("Данные о филиале есть в системе");
        }
        Branch saved = branchRepository.save(branchData);
        return branchMapper.toBranchDto(saved);
    }

    @Override
    public BranchDto update(UUID uuid, BranchDto branch) {
        Branch branchData = branchRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("Данные о филиале не найдены"));
        Branch updated = branchMapper.toBranch(branch);
        updated.setUuid(branchData.getUuid());
        Branch saved = branchRepository.save(updated);
        return branchMapper.toBranchDto(saved);
    }

    @Override
    public BranchDto get(UUID uuid) {
        Branch branchData = branchRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("Данные о филиале не найдены"));
        return branchMapper.toBranchDto(branchData);
    }

    @Override
    public void delete(UUID uuid) {
        Branch branchData = branchRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("Данные о филиале не найдены"));
        branchRepository.delete(branchData);
    }

    @Override
    public List<BranchDto> getAll(int offset, int limit) {
        return branchRepository.findAll(PageRequest.of(offset, limit)).stream()
                .sorted(Comparator.comparing(Branch::getCode))
                .map(branchMapper::toBranchDto).toList();
    }
}