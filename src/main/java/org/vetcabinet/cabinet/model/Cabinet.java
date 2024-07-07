package org.vetcabinet.cabinet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.vetcabinet.branches.model.Branch;

import java.util.UUID;

@Data
@Entity
@Table(name = "clinic_cabinets")
@AllArgsConstructor
@NoArgsConstructor
public class Cabinet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    @ManyToOne
    @JoinColumn(name = "branch_uuid")
    private Branch branch;
    private Long number;
    private String name;
    private Integer floor;
}
