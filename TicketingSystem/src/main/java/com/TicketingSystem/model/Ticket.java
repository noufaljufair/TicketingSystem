package com.TicketingSystem.model;

import com.TicketingSystem.model.enums.Category;
import com.TicketingSystem.model.enums.TicketStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@Entity
@Table(name = "tickets")
@NoArgsConstructor
public class Ticket extends Auditable {

    @ManyToOne
    @JoinColumn(name = "user_id")@JsonBackReference
    private User user;

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "{error.ticket.subject.required}")
    @Size(min = 2, max = 30, message ="{error.ticket.subject.size}")
    private String subject;

    @Lob
    @Column(nullable = false)
    @NotBlank(message = "{error.ticket.description.required}")
    @Type(type = "org.hibernate.type.TextType")
    @Size(min = 10, message ="{error.ticket.description.size}")
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
