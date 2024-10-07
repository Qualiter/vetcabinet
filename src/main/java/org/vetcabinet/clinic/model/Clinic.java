package org.vetcabinet.clinic.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "clinics")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    @Column(name = "code", nullable = false, unique = true)
    private String code;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "short_name", nullable = false)
    private String shortName;
    @Enumerated(value = EnumType.STRING)
    private ClinicType type;
}