package org.vetcabinet.patients.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @OneToMany
    @JoinColumn(name = "patient_id", nullable = false)
    private List<Mark> mark;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "short_name", nullable = false)
    private String shortName;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "animal_species", nullable = false,
            foreignKey = @ForeignKey(name = "ANIMAL_SPECIES_FK"))
    private Specie specie;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "breed_id", nullable = false,
            foreignKey = @ForeignKey(name = "BREED_ID_FK"))
    private Breed breed;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "sex_id", nullable = false,
            foreignKey = @ForeignKey(name = "SEX_ID_FK"))
    private Sex sex;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "color_id", nullable = false,
            foreignKey = @ForeignKey(name = "COLOR_ID_FK"))
    private Color color;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "hair_type_id", nullable = false,
            foreignKey = @ForeignKey(name = "HAIR_TYPE_ID_FK"))
    private HairType hairType;

    @Column(name = "is_sterilized", nullable = false)
    private Boolean isSterilized;

    @Column(nullable = false)
    private Date birthday;

    @Column(name = "is_birthday_defined", nullable = false)
    private Boolean isBirthdayDefined;

    @Column(name = "unique_markings")
    private String uniqueMarkings;
}
