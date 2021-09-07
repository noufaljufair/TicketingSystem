package com.TicketingSystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@AllArgsConstructor
public class ChangePasswordDto {
    @NotNull(message = "{error.user.password.required}")
    private String currentPassword;

    @NotNull(message = "{error.user.password.required}")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}", message = "{error.user.password.invalidPattern}")
    private String password;

}
