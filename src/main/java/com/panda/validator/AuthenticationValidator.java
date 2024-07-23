package com.panda.validator;

import com.panda.model.Authentication;
import com.panda.repository.AuthenticationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthenticationValidator implements Validator {

    private final AuthenticationRepository authenticationRepository;

    @Override
    public boolean supports(Class<?> clazz) {

        return Authentication.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Authentication authentication = (Authentication) target;

        boolean existsEmployeeId = authenticationRepository.findAll().stream()
                .anyMatch(auth1 -> authentication.getEmployeeId().equals(auth1.getEmployeeId()));

        if (existsEmployeeId) {
            errors.rejectValue("id", "");
        }

        boolean existsLogin = authenticationRepository.findAll().stream()
                .anyMatch(auth -> authentication.getLogin().equals(auth.getLogin()));

        if (existsLogin) {
            errors.rejectValue("login", "");
        }
    }
}
