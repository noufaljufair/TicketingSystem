package com.TicketingSystem.dto.request;

import com.TicketingSystem.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
public class UpdateUserRequest {
    @NotBlank(message = "{error.user.firstname.required}")
    @Size(min = 2, max = 15, message ="{error.user.firstname.size}")
    private String firstName;


    @NotBlank(message = "{error.user.lastname.required}")
    @Size(min = 2, max = 15, message ="{error.user.lastname.size}")
    private String lastName;

    @NotNull(message = "{error.user.gender.required}")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    private int avatar;
}
