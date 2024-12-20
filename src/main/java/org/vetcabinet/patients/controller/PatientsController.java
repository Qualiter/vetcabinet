package org.vetcabinet.patients.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.vetcabinet.patients.dto.PatientDto;
import org.vetcabinet.patients.dto.PatientUpdateDto;
import org.vetcabinet.patients.service.PatientsService;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Validated
@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class PatientsController {
    private final PatientsService service;

    @PostMapping
    @ResponseStatus(CREATED)
    public PatientDto create(@Valid @RequestBody PatientDto patient) {
        return service.create(patient);
    }

    @PatchMapping
    @ResponseStatus(OK)
    public PatientUpdateDto update(@Valid @RequestBody PatientUpdateDto patient, @PathVariable UUID id) {
        return service.update(patient, id);
    }
}
