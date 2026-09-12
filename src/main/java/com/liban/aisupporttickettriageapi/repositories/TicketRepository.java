package com.liban.aisupporttickettriageapi.repositories;

import com.liban.aisupporttickettriageapi.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {
}
