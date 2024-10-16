package org.vetcabinet.cabinet.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.vetcabinet.cabinet.dto.CabinetDto;
import org.vetcabinet.cabinet.service.CabinetService;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@Validated
@RestController
@RequestMapping("/cabinets")
@RequiredArgsConstructor
public class CabinetController {
    private final CabinetService service;

    @PostMapping
    @ResponseStatus(CREATED)
    public CabinetDto create(@Valid @RequestBody CabinetDto cabinet) {
        return service.create(cabinet);
    }

    @PatchMapping("/{uuid}")
    public CabinetDto update(@RequestParam UUID uuid, @RequestBody CabinetDto cabinet) {
        return service.update(uuid, cabinet);
    }

    @GetMapping("/{uuid}")
    public CabinetDto get(@RequestParam UUID uuid) {
        return service.get(uuid);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@RequestParam UUID uuid) {
        service.delete(uuid);
    }
}