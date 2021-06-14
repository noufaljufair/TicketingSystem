package com.example.TicketingSystem.model;

import com.example.TicketingSystem.model.enums.Gender;
import com.example.TicketingSystem.model.enums.UserType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "USERS")
@NoArgsConstructor
public class User extends Auditable {


    @OneToMany(fetch = FetchType.EAGER ,mappedBy = "user")@JsonManagedReference
    private List<Ticket> tickets = new ArrayList<>();


    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = " First name must not be blank")
    @Size(min = 2, max = 15, message ="First Name must be between 2 and 15 characters")
    private String firstName;


    @Column(nullable = false)
    @NotBlank(message = " Last name must not be blank")
    @Size(min = 2, max = 15, message ="Last Name must be between 2 and 15 characters")
    private String lastName;


    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}", message = "Password must be at least 8 characters, one uppercase, one lowercase and one digit at least")
    @Column(nullable = false)
    @NotEmpty(message = "Must not be null")
    private String password;




    @Column(nullable = false, unique = true)
    @NotBlank(message = " Email  must not be blank")
    @Email
    private String email;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    //@NotEmpty(message = "Must not be null")
    private Gender gender;


    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    @Column
    private byte [] avatar;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    //@NotEmpty(message = "Must not be null")
    private UserType type;


    public User(long id, String firstName , String lastName, String password, String email , byte [] avatar, UserType type, Gender gender, List<Ticket> tickets) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.avatar = avatar;
        this.type = type;
        this.gender = gender;
        this.tickets = tickets;
    }

}
