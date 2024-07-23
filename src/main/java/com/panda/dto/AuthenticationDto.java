package com.panda.dto;

import lombok.*;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDto {

    private UUID id;

    private String password;

    private String login;

    private UUID employeeId;
}
