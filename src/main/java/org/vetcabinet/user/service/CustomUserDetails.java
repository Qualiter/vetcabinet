package org.vetcabinet.user.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.vetcabinet.user.model.User;
import java.util.List;

import java.util.Collection;
import java.util.UUID;

@Builder
@AllArgsConstructor
@Slf4j
public class CustomUserDetails implements UserDetails {
    private final String password;
    private final String username;
    private final Collection<? extends GrantedAuthority> authorities;
    @Getter
    private final UUID uuid;
    private final boolean isEnabled;
    private final boolean accountNotLocked;
    private final boolean accountNotExpired;
    private final boolean credentialsNotExpired;

    public CustomUserDetails(final User user) {
        this.uuid = user.getUuid();
        this.username = user.getLogin();
        this.password = user.getPassword();
        this.isEnabled = true;
        this.accountNotLocked = true;
        this.accountNotExpired = true;
        this.credentialsNotExpired = true;
        this.authorities = List.of(user.getRole());
    }

    public CustomUserDetails(final UUID id,
                             final String username,
                             final String password,
                             final Collection<? extends GrantedAuthority> authorities) {
        this.uuid = id;
        this.username = username;
        this.password = password;
        this.isEnabled = true;
        this.accountNotLocked = true;
        this.accountNotExpired = true;
        this.credentialsNotExpired = true;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNotExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNotLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNotExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    @Override
    public String toString() {
        return "{\"user_id\":\"" + uuid + "\"," +
                "\"username\":\"" + username + "\"," +
                "\"user_role\":\"" + authorities + "\"," +
                "\"password\":\"" + password + "\"}";
    }
}
