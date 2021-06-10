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
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table
@NoArgsConstructor
public class User extends Auditable {

    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets = new ArrayList<>();


    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(name = "firstName",nullable = false, unique = false)
    @NotEmpty(message = "Must not be null")
    private String firstName;


    @Column(name = "lastname",nullable = false, unique = false)
    @NotEmpty(message = "Must not be null")
    private String lastName;



    @Column(name = "password",nullable = false, unique = false)
    @NotEmpty(message = "Must not be null")
    private String password;




    @Column(name = "email",nullable = false, unique = true)
    @NotEmpty(message = "Must not be null")
    private String email;


    @Enumerated(EnumType.STRING)
    @Column(name = "gender",nullable = false, unique = false)
    //@NotEmpty(message = "Must not be null")
    private Gender gender;


    @Lob
    @Column(name = "avatar",nullable = false, unique = false, columnDefinition = "BLOB")
    //@NotEmpty(message = "Must not be null")
    private byte[] avatar;


    @Enumerated(EnumType.STRING)
    @Column(name = "type",nullable = false, unique = false)
    //@NotEmpty(message = "Must not be null")
    private UserType type;


    public User(long id, String firstName , String lastName, String password, String email, byte [] avatar , UserType type, Gender gender, List<Ticket> tickets) {
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
