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
    @NotBlank(message = "{error.user.firstname.required}")
    @Size(min = 2, max = 15, message ="{error.user.firstname.size}")
    private String firstName;

    @Column(nullable = false)
    @NotBlank(message = "{error.user.lastname.required}")
    @Size(min = 2, max = 15, message ="{error.user.lastname.size}")
    private String lastName;

    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}", message = "{error.user.password.invalidPattern}")
    @Column(nullable = false)
    @NotEmpty(message = "{error.user.password.required}")
    private String password;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "{error.user.email.required}")
    @Email(message="{error.user.email.invalid}")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    @Column
    private byte [] avatar;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
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
