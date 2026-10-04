package com.liban.aisupporttickettriageapi.repositories;

import com.liban.aisupporttickettriageapi.model.Ticket;
import com.liban.aisupporttickettriageapi.model.User;
import com.liban.aisupporttickettriageapi.model.enums.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {

    List<Ticket> findTicketsByUser(User user);

    List<Ticket> findTicketSByPriority(Priority priority);
}
