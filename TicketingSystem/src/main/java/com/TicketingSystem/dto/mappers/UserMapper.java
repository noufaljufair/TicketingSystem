package com.TicketingSystem.dto.mappers;

import com.TicketingSystem.dto.request.RegistrationRequest;
import com.TicketingSystem.dto.request.UpdateUserRequest;
import com.TicketingSystem.dto.response.UserDto;
import com.TicketingSystem.model.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {
    //@Mapping(target = "role", constant = "CLIENT")
    User toUser(RegistrationRequest registrationRequest);

    UserDto toUserDto(User user);
}
