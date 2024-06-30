package com.vetcabinet.branches.model;

import com.vetcabinet.clinic.model.Clinic;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@Table(name = "clinic_branches")
@AllArgsConstructor
@NoArgsConstructor
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    private String code;
    @ManyToOne
    @JoinColumn(name = "clinic_uuid")
    private Clinic clinic;
    @Column(name = "is_main")
    private Boolean isMain;
    private String name;
    @Column(name = "short_name")
    private String shortName;
    @Column(name = "full_address")
    private String fullAddress;
    private String address;
    private BigDecimal latitude;
    private BigDecimal longitude;
    @Column(name = "is_storeyed")
    private Boolean isStoreyed;
    private Integer floor;

}
