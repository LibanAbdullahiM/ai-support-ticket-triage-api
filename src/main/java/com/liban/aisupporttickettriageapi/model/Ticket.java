package com.liban.aisupporttickettriageapi.model;

import com.liban.aisupporttickettriageapi.model.enums.Category;
import com.liban.aisupporttickettriageapi.model.enums.Priority;
import com.liban.aisupporttickettriageapi.model.enums.TicketStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "tickets")
public class Ticket extends BaseEntity{

    private String title;
    private String description;
    private Category category;
    private Priority priority;
    private TicketStatus status;
    private String aiSuggestedReply;
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
