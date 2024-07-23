package com.panda.repository;

import com.panda.dto.EmployeeDto;
import com.panda.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    Optional<Employee> findEmployeeByAuthenticationLogin(String login);

    Optional<Employee> findByName(String name);

}
