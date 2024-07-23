package com.panda.repository;

import com.panda.model.Authentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthenticationRepository extends JpaRepository<Authentication, UUID> {
    Optional<Authentication> findByLogin(String login);
    Optional<Authentication> findByEmployeeId(UUID employeeId);
//
//    Optional<Authentication> findAuthenticationByLogin(String login);
}
