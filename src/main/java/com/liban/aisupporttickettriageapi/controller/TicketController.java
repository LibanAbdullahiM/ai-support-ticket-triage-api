package com.liban.aisupporttickettriageapi.controller;

import com.liban.aisupporttickettriageapi.dtos.request.TicketRequestDTO;
import com.liban.aisupporttickettriageapi.dtos.response.TicketResponseDTO;
import com.liban.aisupporttickettriageapi.services.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<TicketResponseDTO> getTickets(@RequestParam(required = false) String priority) {
        if (priority != null) {
            return ticketService.getTicketsByPriority(priority);
        }

        return ticketService.getTickets();
    }

    @GetMapping("/{ticket_id}")
    @ResponseStatus(HttpStatus.OK)
    public TicketResponseDTO getTicketById(@PathVariable UUID ticket_id) {
        return ticketService.getTicketById(ticket_id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TicketResponseDTO createTicket(@Valid
                                              @RequestBody TicketRequestDTO ticketRequestDTO) {
        return ticketService.save(ticketRequestDTO);
    }

    @PutMapping("/{ticket_id}/status")
    @ResponseStatus(HttpStatus.OK)
    public TicketResponseDTO updateStatus(@PathVariable UUID ticket_id,
                                          @RequestParam String status) {
        return ticketService.updateStats(ticket_id, status);
    }

    @DeleteMapping("/{ticket_id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTicketById(@PathVariable UUID ticket_id) {
        ticketService.deleteById(ticket_id);
    }
}
