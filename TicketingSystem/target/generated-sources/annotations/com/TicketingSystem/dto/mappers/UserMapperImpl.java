package com.TicketingSystem.dto.mappers;

import com.TicketingSystem.dto.request.RegistrationRequest;
import com.TicketingSystem.dto.response.UserDto;
import com.TicketingSystem.model.User;
import com.TicketingSystem.model.enums.Gender;
import com.TicketingSystem.model.enums.UserRole;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-08T14:49:18+0300",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 16.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(RegistrationRequest registrationRequest) {
        if ( registrationRequest == null ) {
            return null;
        }

        User user = new User();

        user.setFirstName( registrationRequest.getFirstName() );
        user.setLastName( registrationRequest.getLastName() );
        user.setPassword( registrationRequest.getPassword() );
        user.setEmail( registrationRequest.getEmail() );
        user.setGender( registrationRequest.getGender() );
        user.setAvatar( registrationRequest.getAvatar() );
        user.setRole( registrationRequest.getRole() );

        return user;
    }

    @Override
    public UserDto toUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        Long id = null;
        String firstName = null;
        String lastName = null;
        String email = null;
        Gender gender = null;
        UserRole role = null;

        id = user.getId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
        gender = user.getGender();
        role = user.getRole();

        UserDto userDto = new UserDto( id, firstName, lastName, email, gender, role );

        return userDto;
    }
}
