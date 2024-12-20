package org.vetcabinet.patients.dto;


import lombok.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PatientUpdateDto {

    private String fullName;

    private String shortName;

    //private SpecieDto specieDto;

    //private BreedDto breedDto;

    //private SexDto sexDto;

    private ColorDto colorDto;

    //private HairTypeDto hairTypeDto;

    private Boolean isSterilized;

    private Date birthday;

    private Boolean isBirthdayDefined;

    private String uniqueMarkings;
}
