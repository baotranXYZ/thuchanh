package com.example.tuan5_ketnoicsdl.entity;
import com.example.tuan5_ketnoicsdl.repository.IuserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

import java.util.HashSet;
import java.util.Set;

public class CustomUserDetail implements UserDetails {
    private final User user;
    private final IuserRepository userRepositpry;

    public CustomUserDetail(User user, IuserRepository userRepository){
        this.user = user;
        this.userRepositpry = userRepository;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        String[] roles = userRepositpry.getRolesOfUser(user.getId());
        for (String role : roles){
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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
