package com.example.TicketingSystem.model;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.example.TicketingSystem.model.enums.Category;
import com.example.TicketingSystem.model.enums.TicketStatus;
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



    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false, unique = false)
    @NotEmpty(message = "Must not be null")
    private TicketStatus status;




    @Enumerated(EnumType.STRING)
    @Column(name = "category",nullable = false, unique = false)
    @NotEmpty(message = "Must not be null")
    private Category category;





    public Ticket( Users user, long l, String subject , String description, TicketStatus status, Category category) {
        this.user = user;
        this.id = l;
        this.subject = subject;
        this.description = description;
        this.status = status;
        this.category = category;

    }
}
