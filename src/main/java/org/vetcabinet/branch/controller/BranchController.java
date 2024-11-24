package org.vetcabinet.branch.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.vetcabinet.branch.dto.BranchDto;
import org.vetcabinet.branch.service.BranchService;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@Validated
@RestController
@RequestMapping("/branches")
@RequiredArgsConstructor
public class BranchController {
    private final BranchService service;

    @PostMapping
    @ResponseStatus(CREATED)
    public BranchDto create(@Valid @RequestBody BranchDto branch) {
        return service.create(branch);
    }

    @PatchMapping("/{uuid}")
    public BranchDto update(@PathVariable UUID uuid, @RequestBody BranchDto branch) {
        return service.update(uuid, branch);
    }

    @GetMapping("/{uuid}")
    public BranchDto get(@PathVariable UUID uuid) {
        return service.get(uuid);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable UUID uuid) {
        service.delete(uuid);
    }

    @GetMapping
    public List<BranchDto> getAll(@RequestParam(defaultValue = "0") int offset,
                                  @RequestParam(defaultValue = "10") int limit) {
        return service.getAll(offset, limit);
    }
}