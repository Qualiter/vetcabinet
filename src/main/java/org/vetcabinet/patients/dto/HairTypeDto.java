package org.vetcabinet.patients.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class HairTypeDto {
    @NotBlank
    private String name;
}
