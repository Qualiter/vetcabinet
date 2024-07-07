package org.vetcabinet.clinic.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.vetcabinet.clinic.dto.ClinicDto;
import org.vetcabinet.clinic.service.ClinicService;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@Validated
@RestController
@RequestMapping("/clinics")
@RequiredArgsConstructor
public class ClinicController {
    private final ClinicService service;

    @PostMapping
    @ResponseStatus(CREATED)
    public ClinicDto create(@Valid @RequestBody ClinicDto clinic) {
        return service.create(clinic);
    }

    @PutMapping("/{uuid}")
    public ClinicDto update(@RequestParam UUID uuid, @RequestBody ClinicDto clinic) {
        return service.update(uuid, clinic);
    }

    @GetMapping("/{uuid}")
    public ClinicDto get(@RequestParam UUID uuid) {
        return service.get(uuid);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@RequestParam UUID uuid) {
        service.delete(uuid);
    }
}
