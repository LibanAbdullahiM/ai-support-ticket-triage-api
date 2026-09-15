package com.liban.aisupporttickettriageapi.services;

import com.liban.aisupporttickettriageapi.dtos.request.TicketRequestDTO;
import com.liban.aisupporttickettriageapi.dtos.response.TicketResponseDTO;

import java.util.Set;
import java.util.UUID;

public interface TicketService {

    Set<TicketResponseDTO> getTickets();

    Set<TicketResponseDTO> getTicketsByUser(UUID userId);

    Set<TicketResponseDTO> getTicketsByPriority(String priority);

    TicketResponseDTO getTicketById(UUID id);

    TicketResponseDTO save(TicketRequestDTO ticketRequestDTO);

    TicketResponseDTO updateStats(UUID ticket_id, String status);

    void deleteById(UUID ticket_id);
}
