package org.vetcabinet.cabinet.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.vetcabinet.branch.dto.BranchDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CabinetDto {
    @NotBlank
    private BranchDto branch;

    private Integer number;

    @NotBlank
    private String name;

    @NotBlank
    @Max(100)
    @Min(-10)
    private Integer floor;
}