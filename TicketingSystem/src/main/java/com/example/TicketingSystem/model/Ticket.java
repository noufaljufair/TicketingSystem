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


@Data
@Entity
@Table(name = "Ticket")
@NoArgsConstructor
public class Ticket extends Auditable {



    @ManyToOne
    private Users user;




    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;



    @Column(name = "subject",nullable = false, unique = false)
    @NotEmpty(message = "Must not be null")
    private String subject;




    @Lob
    @Column(name = "description",nullable = false, unique = false)
    @NotEmpty(message = "Must not be null")
    private String description;



    //enum
    @Column(name = "status",nullable = false, unique = false)
    @NotEmpty(message = "Must not be null")
    private String status;




    //enum
    @Column(name = "category",nullable = false, unique = false)
    @NotEmpty(message = "Must not be null")
    private String category;





    public Ticket( Users user, long l, String subject , String description, String status, String category) {
        this.user = user;
        this.id = l;
        this.subject = subject;
        this.description = description;
        this.status = status;
        this.category = category;

    }
}
