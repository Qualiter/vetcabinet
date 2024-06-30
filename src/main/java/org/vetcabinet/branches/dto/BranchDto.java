package org.vetcabinet.branches.dto;

import org.vetcabinet.clinic.model.Clinic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchDto {
    private UUID uuid;
    private String code;
    private Clinic clinic;
    private Boolean isMain;
    private String name;
    private String shortName;
    private String fullAddress;
    private String address;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Boolean isStoreyed;
    private Integer floor;
}
