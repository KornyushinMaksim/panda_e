package com.panda.service;

import com.panda.dto.AuthenticationDto;
import com.panda.dto.EmployeeDto;
import com.panda.mapper.AuthenticationMapper;
import com.panda.model.Authentication;
import com.panda.repository.AuthenticationRepository;
import com.panda.validator.AuthenticationValidator;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationRepository authenticationRepository;
    private final AuthenticationMapper authenticationMapper;
    private final EmployeeService employeeService;
    private final AuthenticationValidator authenticationValidator;
    private final PasswordEncoder passwordEncoder;

    /**
     * создание нового логин/пароля
     * @param authenticationDto
     */
    public void add(AuthenticationDto authenticationDto, UUID employeeId, BindingResult bindingResult) {

        authenticationDto.setPassword(passwordEncoder.encode(authenticationDto.getPassword()));

        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
        Authentication authenticationSaved = authenticationMapper.toEntity(authenticationDto);

        authenticationSaved.setEmployeeId(employeeId);
        authenticationValidator.validate(authenticationSaved, bindingResult);

        if(bindingResult.hasErrors()){
            throw new ValidationException();
        }

        Authentication save = authenticationRepository.save(authenticationSaved);

        employeeDto.setAuthentication(authenticationMapper.toDto(save));
        employeeService.updateEmployee(employeeDto);
    }

    /**
     * изменение логин/пароля
     * @param authenticationDto
     */
    public void update(AuthenticationDto authenticationDto) {

        Authentication authentication = authenticationRepository.findById(authenticationDto.getId())
                        .orElseThrow();
        Authentication authenticationSaved = authenticationMapper.merge(authenticationDto, authentication);

        EmployeeDto employeeDto = employeeService.getEmployeeById(authenticationDto.getEmployeeId());
        employeeDto.setAuthentication(authenticationMapper.toDto(authenticationSaved));

        System.out.println();


        employeeService.updateEmployee(employeeDto);
        authenticationRepository.save(authenticationSaved);
    }

    public List<Authentication> findAll() {
        return authenticationRepository.findAll();
    }

    /**
     * получение аутентификации по id(optional)
     * @param login
     * @return
     */
    public UUID getEmployeeIdByLogin(String login) {
        Authentication entity = authenticationRepository.findByLogin(login).orElseThrow();
        return entity.getEmployeeId();
    }

    public AuthenticationDto getAuthByEmployeeId(UUID employeeId) {

        Authentication entity = authenticationRepository.findByEmployeeId(employeeId).orElseThrow();
        return authenticationMapper.toDto(entity);
    }
}
