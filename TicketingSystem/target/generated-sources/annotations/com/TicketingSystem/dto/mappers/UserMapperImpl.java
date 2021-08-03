package com.TicketingSystem.dto.mappers;

import com.TicketingSystem.dto.request.RegistrationRequest;
import com.TicketingSystem.dto.response.UserDto;
import com.TicketingSystem.model.User;
import com.TicketingSystem.model.enums.Gender;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-08-02T10:13:24+0300",
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
        user.setRole( registrationRequest.getRole() );

        return user;
    }

    @Override
    public UserDto toUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        String firstName = null;
        String lastName = null;
        String email = null;
        Gender gender = null;

        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
        gender = user.getGender();

        UserDto userDto = new UserDto( firstName, lastName, email, gender );

        return userDto;
    }
}
