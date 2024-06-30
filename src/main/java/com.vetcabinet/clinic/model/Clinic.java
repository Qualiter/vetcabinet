package com.vetcabinet.clinic.model;

import com.vetcabinet.enums.ClinicType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@Table(name = "clinics")
@AllArgsConstructor
@NoArgsConstructor
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    private String code;
    private String name;
    @Column(name = "short_name")
    private String shortName;
    private ClinicType type;
}
