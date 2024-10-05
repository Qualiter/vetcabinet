package org.vetcabinet.branches.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.vetcabinet.branches.dto.BranchDto;
import org.vetcabinet.branches.service.BranchService;

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
    public BranchDto update(@RequestParam UUID uuid, @RequestBody BranchDto branch) {
        return service.update(uuid, branch);
    }

    @GetMapping("/{uuid}")
    public BranchDto get(@RequestParam UUID uuid) {
        return service.get(uuid);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@RequestParam UUID uuid) {
        service.delete(uuid);
    }
}