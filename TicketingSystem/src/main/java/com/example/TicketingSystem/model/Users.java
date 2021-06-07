package com.example.TicketingSystem.model;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;


@Data
@Entity
@Table(name = "Users")
@NoArgsConstructor
public class Users extends Auditable {


    @OneToMany
    private List<Ticket> tickets ;




    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(name = "firstName",nullable = false, unique = false)
    @NotEmpty(message = "Must not be null")
    private String fisrtName;


    @Column(name = "lastName",nullable = false, unique = false)
    @NotEmpty(message = "Must not be null")
    private String lastName;



    @Column(name = "password",nullable = false, unique = false)
    @NotEmpty(message = "Must not be null")
    private String password;




    @Column(name = "email",nullable = false, unique = true)
    @NotEmpty(message = "Must not be null")
    private String email;


    //enum
    @Column(name = "gender",nullable = false, unique = false)
    @NotEmpty(message = "Must not be null")
    private int gender;



    @Column(name = "avatar",nullable = false, unique = false)
    @NotEmpty(message = "Must not be null")
    private int avatar;



    @Column(name = "type",nullable = false, unique = false)
    @NotEmpty(message = "Must not be null")
    private int type;



    public Users(List<Ticket> tickets ,long l, String fisrtName , String lastName, String pass, String email, int avatar , int type) {
        this.tickets = tickets;
        this.id = l;
        this.fisrtName = fisrtName;
        this.lastName = lastName;
        this.password = pass;
        this.email = email;
        this.avatar = avatar;
        this.type = type;
    }
}
