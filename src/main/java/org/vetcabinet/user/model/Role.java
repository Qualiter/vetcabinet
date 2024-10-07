package org.vetcabinet.user.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import static jakarta.persistence.EnumType.STRING;

@Data
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(STRING)
    private RoleName name;

    @Override
    public String getAuthority() {
        return this.name.name();
    }
}