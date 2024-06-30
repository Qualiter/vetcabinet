package com.vetcabinet.cabinet.dto;

import com.vetcabinet.branches.model.Branch;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CabinetDto {
    private UUID uuid;
    @NotBlank
    private Branch branch;
    @NotBlank
    private Long number;
    @NotBlank
    private String name;
    @NotBlank
    private Integer floor;
}
