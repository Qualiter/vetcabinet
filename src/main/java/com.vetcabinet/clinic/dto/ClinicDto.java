package com.vetcabinet.clinic.dto;

import com.vetcabinet.enums.ClinicType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClinicDto {
    private UUID uuid;
    @NotBlank
    private String code;
    @NotBlank
    private String name;
    @NotBlank
    private String shortName;
    @NotBlank
    private ClinicType type;
}
