package org.vetcabinet.patients.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PatientDto {

    private String fullName;

    @NotBlank
    private String shortName;

    @NotNull
    private SpecieDto specie;

    @NotNull
    private BreedDto breed;

    @NotNull
    private SexDto sex;

    @NotNull
    private ColorDto color;

    private HairTypeDto hairType;

    @NotNull
    private Boolean isSterilized;

    @DateTimeFormat
    private Date birthday;

    private Boolean isBirthdayDefined;

    private String uniqueMarkings;
}
