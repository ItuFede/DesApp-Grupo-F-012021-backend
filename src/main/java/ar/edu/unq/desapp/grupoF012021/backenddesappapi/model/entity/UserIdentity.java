package ar.edu.unq.desapp.grupoF012021.backenddesappapi.model.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Data
public class UserIdentity implements UserDetails {
    private final UserEntity associatedUserEntity;

    public UserIdentity(UserEntity user) {
        this.associatedUserEntity = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(associatedUserEntity.getAuthority()));
    }

    @Override
    public String getPassword() {
        return associatedUserEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return associatedUserEntity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
