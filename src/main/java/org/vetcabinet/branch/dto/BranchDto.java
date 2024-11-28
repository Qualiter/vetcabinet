package org.vetcabinet.branch.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.vetcabinet.address.dto.RegisterAddressDto;
import org.vetcabinet.clinic.dto.ClinicDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchDto {
    @NotBlank
    private ClinicDto clinic;

    @NotBlank
    private Boolean isMain;

    @NotBlank
    private String name;

    @NotBlank
    private String shortName;

    @NotBlank
    private RegisterAddressDto address;

    @NotBlank
    private Boolean isStoreyed;

    @NotBlank
    @Max(100)
    @Min(-10)
    private Integer floor;
}