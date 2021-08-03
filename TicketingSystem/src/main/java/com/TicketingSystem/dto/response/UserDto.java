package com.TicketingSystem.dto.response;

import com.TicketingSystem.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@AllArgsConstructor
@Getter
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    @Enumerated(EnumType.STRING)
    private Gender gender;
}
