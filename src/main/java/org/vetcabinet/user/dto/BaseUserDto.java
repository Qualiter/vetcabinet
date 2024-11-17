package org.vetcabinet.user.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BaseUserDto {
    private String name;

    private String surname;
}
