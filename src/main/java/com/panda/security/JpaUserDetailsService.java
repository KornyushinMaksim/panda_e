package com.panda.security;

import com.panda.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    private final EmployeeService employeeService;
//    private final AuthenticationService authenticationService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername");

        System.out.println(employeeService.findEmployeeByAuthenticationLogin(username).get().getRole());

        return employeeService.findEmployeeByAuthenticationLogin(username)
                .map(AuthEmployee::new)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь с username " + username + " не найден!"));
    }
}
