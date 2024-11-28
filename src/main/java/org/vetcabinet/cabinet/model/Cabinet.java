package org.vetcabinet.cabinet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.vetcabinet.branch.model.Branch;

import java.util.UUID;

@Entity
@Table(name = "clinic_cabinets")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cabinet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "branch_uuid")
    private Branch branch;

    @Column(name = "number", nullable = false, unique = true)
    private Integer number;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "floor", unique = true)
    private Integer floor;
}
