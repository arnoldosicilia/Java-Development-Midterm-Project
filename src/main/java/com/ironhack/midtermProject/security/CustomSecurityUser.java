package com.ironhack.midtermProject.security;

import com.ironhack.midtermProject.model.Role;
import com.ironhack.midtermProject.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CustomSecurityUser extends User implements UserDetails {

    private static final long serialVersionUID = -4381938875186527688L;

    public CustomSecurityUser(User user) {
        System.out.println(user + "IN custom security");

        this.setRoles(user.getRoles());
        this.setId(user.getId());
        this.setPassword(user.getPassword());
        this.setName(user.getUsername());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new HashSet<>();
        Set<Role> roles = this.getRoles();
        for( Role role : roles ) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole()));
        }
        return authorities;
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
