package com.TicketingSystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class AuthenticationRequest {

    @NotBlank(message = "{error.user.email.required}")
    @Email(message="{error.user.email.invalid}")
    private String email;

    @NotBlank(message = "{error.user.password.required}")
    private String password;
}
