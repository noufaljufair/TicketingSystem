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


@Data
@Entity
@Table(name = "tickets")
@NoArgsConstructor
public class Ticket extends Auditable {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;


    @Column(nullable = false)
    @NotEmpty(message = "Must not be null")
    private String subject;




    @Lob
    @Column(nullable = false)
    @NotEmpty(message = "Must not be null")
    private String description;



    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketStatus status;




    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;





    public Ticket(User user, long id, String subject , String description, TicketStatus status, Category category) {
        this.user = user;
        this.id = id;
        this.subject = subject;
        this.description = description;
        this.status = status;
        this.category = category;

    }
}
