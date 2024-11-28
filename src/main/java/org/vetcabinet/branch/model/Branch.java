package org.vetcabinet.branch.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.vetcabinet.address.model.Address;
import org.vetcabinet.clinic.model.Clinic;

import java.util.UUID;

@Entity
@Table(name = "clinic_branches")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @ManyToOne
    @JoinColumn(name = "clinic_uuid")
    private Clinic clinic;

    @Column(name = "is_main", nullable = false)
    private Boolean isMain;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "short_name", nullable = false)
    private String shortName;

    @Column(name = "address", nullable = false)
    private Address address;

    @Column(name = "is_storeyed")
    private Boolean isStoreyed;

    @Column(name = "floor", unique = true)
    private Integer floor;
}
