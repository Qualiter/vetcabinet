package org.vetcabinet.patients.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.vetcabinet.patients.model.IdentifierType;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MarkDto {
    @NotBlank
    private String identifierValue;

    @NotBlank
    private IdentifierType identifierType;
}
