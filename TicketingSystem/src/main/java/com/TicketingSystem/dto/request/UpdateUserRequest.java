package com.TicketingSystem.dto.request;

import com.TicketingSystem.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
public class UpdateUserRequest {
    @NotBlank(message = "{error.user.firstname.required}")
    @Size(min = 2, max = 15, message ="{error.user.firstname.size}")
    private String firstName;

    @Column(nullable = false)
    @NotBlank(message = "{error.user.lastname.required}")
    @Size(min = 2, max = 15, message ="{error.user.lastname.size}")
    private String lastName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    @Column
    private byte [] avatar;


}
