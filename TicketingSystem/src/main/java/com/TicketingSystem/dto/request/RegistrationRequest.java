package com.TicketingSystem.dto.request;

import com.TicketingSystem.model.enums.Gender;
import com.TicketingSystem.model.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
public class RegistrationRequest {

    @NotBlank(message = "{error.user.firstname.required}")
    @Size(min = 2, max = 15, message ="{error.user.firstname.size}")
    private String firstName;

    @NotBlank(message = "{error.user.lastname.required}")
    @Size(min = 2, max = 15, message ="{error.user.lastname.size}")
    private String lastName;

    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}", message = "{error.user.password.invalidPattern}")
    @NotEmpty(message = "{error.user.password.required}")
    private String password;

    @NotBlank(message = "{error.user.email.required}")
    @Email(message="{error.user.email.invalid}")
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;
}
