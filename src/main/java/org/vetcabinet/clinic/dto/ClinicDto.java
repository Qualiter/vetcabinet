package org.vetcabinet.clinic.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.vetcabinet.enums.ClinicType;

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

    public ClinicDto(String code, String name, String shortName, ClinicType type) {
        this.code = code;
        this.name = name;
        this.shortName = shortName;
        this.type = type;
    }
}
