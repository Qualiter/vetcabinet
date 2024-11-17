package org.vetcabinet.user.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class AuthResponseDto {

    private String token;

    private String username;

    private Collection<? extends GrantedAuthority> roles;
}