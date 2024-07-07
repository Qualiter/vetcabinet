package org.vetcabinet.branches.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.vetcabinet.clinic.model.Clinic;

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

    public BranchDto(String code,
                     Clinic clinic,
                     Boolean isMain,
                     String name,
                     String shortName,
                     String fullAddress,
                     String address,
                     BigDecimal latitude,
                     BigDecimal longitude,
                     Boolean isStoreyed,
                     Integer floor) {
        this.code = code;
        this.clinic = clinic;
        this.isMain = isMain;
        this.name = name;
        this.shortName = shortName;
        this.fullAddress = fullAddress;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isStoreyed = isStoreyed;
        this.floor = floor;
    }
}
