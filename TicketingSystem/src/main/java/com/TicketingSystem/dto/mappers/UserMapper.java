package com.TicketingSystem.dto.mappers;

import com.TicketingSystem.dto.request.RegistrationRequest;
import com.TicketingSystem.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "role", constant = "USER")
    User toUser(RegistrationRequest registrationRequest);
}
