package org.vetcabinet.clinic.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.vetcabinet.clinic.dto.ClinicDto;
import org.vetcabinet.clinic.service.ClinicService;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@Validated
@RestController
@RequestMapping("/clinics")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class ClinicController {
    private final ClinicService service;

    @PostMapping
    @ResponseStatus(CREATED)
    public ClinicDto create(@Valid @RequestBody ClinicDto clinic) {
        return service.create(clinic);
    }

    @PatchMapping("/{uuid}")
    public ClinicDto update(@PathVariable UUID uuid, @RequestBody ClinicDto clinic) {
        return service.update(uuid, clinic);
    }

    @GetMapping("/{uuid}")
    public ClinicDto get(@PathVariable UUID uuid) {
        return service.get(uuid);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable UUID uuid) {
        service.delete(uuid);
    }

    @GetMapping
    public List<ClinicDto> getAll(@RequestParam(defaultValue = "0") int offset,
                                  @RequestParam(defaultValue = "10") int limit) {
        return service.getAll(offset, limit);
    }
}