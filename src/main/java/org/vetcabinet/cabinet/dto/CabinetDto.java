package org.vetcabinet.cabinet.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.vetcabinet.branches.model.Branch;

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
  
    public CabinetDto(Branch branch, Long number, String name, Integer floor) {
        this.branch = branch;
        this.number = number;
        this.name = name;
        this.floor = floor;
    }
}