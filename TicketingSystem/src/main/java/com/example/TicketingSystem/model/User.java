package com.example.TicketingSystem.model;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.example.TicketingSystem.model.enums.Gender;
import com.example.TicketingSystem.model.enums.UserType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.OneToMany;
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
    @NotEmpty(message = "Must not be null")
    private String firstName;


    @Column(nullable = false)
    @NotEmpty(message = "Must not be null")
    private String lastName;



    @Column(nullable = false)
    @NotEmpty(message = "Must not be null")
    private String password;




    @Column(nullable = false, unique = true)
    @NotEmpty(message = "Must not be null")
    private String email;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    //@NotEmpty(message = "Must not be null")
    private Gender gender;


    @Lob
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
