package com.panda.security;

import com.panda.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@RequiredArgsConstructor
public class AuthEmployee implements UserDetails {

    private final Employee employee;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println("getRole " + employee.getRole());
        System.out.println(employee);
        return Collections.singleton(employee.getRole());
    }

    @Override
    public String getPassword() {
        System.out.println("getPassword");
        System.out.println(employee);
        System.out.println();

        return employee.getAuthentication().getPassword();
    }

    @Override
    public String getUsername() {
        System.out.println("getUsername");
        System.out.println("getUsername" + employee.getAuthentication().getLogin());

        return employee.getAuthentication().getLogin();
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
