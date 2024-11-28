package org.vetcabinet.clinic.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.vetcabinet.clinic.model.ClinicType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClinicDto {
    @NotBlank
    private String code;

    @NotBlank
    private String name;

    @NotBlank
    private String shortName;

    @NotBlank
    private ClinicType type;
}
