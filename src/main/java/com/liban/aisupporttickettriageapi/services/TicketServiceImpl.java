package com.liban.aisupporttickettriageapi.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liban.aisupporttickettriageapi.dtos.request.TicketRequestDTO;
import com.liban.aisupporttickettriageapi.dtos.response.TicketResponseDTO;
import com.liban.aisupporttickettriageapi.exceptions.ResourceNotFoundException;
import com.liban.aisupporttickettriageapi.mapper.TicketMapper;
import com.liban.aisupporttickettriageapi.model.Ticket;
import com.liban.aisupporttickettriageapi.model.User;
import com.liban.aisupporttickettriageapi.model.enums.Category;
import com.liban.aisupporttickettriageapi.model.enums.Priority;
import com.liban.aisupporttickettriageapi.model.enums.TicketStatus;
import com.liban.aisupporttickettriageapi.repositories.TicketRepository;
import com.liban.aisupporttickettriageapi.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final UserRepository userRepository;
    private final AiTriageService aiTriageService;

    public TicketServiceImpl(TicketRepository ticketRepository,
                             TicketMapper ticketMapper,
                             UserRepository userRepository,
                             AiTriageService aiTriageService) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
        this.userRepository = userRepository;
        this.aiTriageService = aiTriageService;
    }

    @Override
    public Set<TicketResponseDTO> getTickets() {

        Set<Ticket> tickets = new HashSet<>(ticketRepository.findAll());

        Set<TicketResponseDTO> ticketResponseDTOs = new HashSet<>();

        for (Ticket ticket : tickets) {
            ticketResponseDTOs.add(ticketMapper.toTicketResponseDTO(ticket));
        }

        return ticketResponseDTOs;
    }

    @Override
    public Set<TicketResponseDTO> getTicketsByUser(UUID userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }

        Set<Ticket> tickets = new HashSet<>(ticketRepository.findTicketsByUser(user));

        Set<TicketResponseDTO> ticketResponseDTOs = new HashSet<>();

        for (Ticket ticket : tickets) {
            ticketResponseDTOs.add(ticketMapper.toTicketResponseDTO(ticket));
        }

        return ticketResponseDTOs;
    }

    @Override
    public Set<TicketResponseDTO> getTicketsByPriority(String priority) {

        Set<Ticket> tickets = new HashSet<>(ticketRepository.findAll());

        Set<Ticket> ticketsByPriority = filterByPriority(tickets, priority);

        Set<TicketResponseDTO> ticketResponseDTOs = new HashSet<>();

        for (Ticket ticket : ticketsByPriority) {
            ticketResponseDTOs.add(ticketMapper.toTicketResponseDTO(ticket));
        }

        return ticketResponseDTOs;
    }

    @Override
    public TicketResponseDTO getTicketById(UUID id) {
        Ticket ticket = ticketRepository.findById(id).orElse(null);

        if (ticket == null) {
            throw new ResourceNotFoundException("Ticket not found");
        }

        return ticketMapper.toTicketResponseDTO(ticket);
    }

    @Override
    public TicketResponseDTO save(TicketRequestDTO ticketRequestDTO) {
        String aiTriage = aiTriageService.analyzeTicket(ticketRequestDTO.getTitle(), ticketRequestDTO.getDescription());

        //convert the AI replied JSON into Strings
        ObjectMapper objectMapper = new ObjectMapper();
        String category = "";
        String priority = "";
        String aiSuggestedReply = "";

        try {
            JsonNode rootNode = objectMapper.readTree(aiTriage);

            category = rootNode.get("category").asText();
            priority = rootNode.get("priority").asText();
            aiSuggestedReply = rootNode.get("aiSuggestedReply").asText();

        }catch (Exception e) {
            e.printStackTrace();
        }

        Ticket ticket = ticketMapper.toTicket(ticketRequestDTO);
        ticket.setCreatedAt(LocalDate.now());
        ticket.setStatus(TicketStatus.OPEN);
        ticket.setCategory(Category.valueOf(category));
        ticket.setPriority(Priority.valueOf(priority));
        ticket.setAiSuggestedReply(aiSuggestedReply);

        //set the user after implementing the security part
        //Save to db

        return ticketMapper.toTicketResponseDTO(ticket);
    }

    @Override
    public TicketResponseDTO updateStats(UUID ticket_id, String status) {
        Ticket ticket = ticketRepository.findById(ticket_id).orElse(null);
        if (ticket == null) {
            throw new ResourceNotFoundException("Ticket not found");
        }

        ticket.setStatus(TicketStatus.valueOf(status));
        ticketRepository.save(ticket);

        return ticketMapper.toTicketResponseDTO(ticket);
    }

    @Override
    public void deleteById(UUID ticket_id) {
        ticketRepository.deleteById(ticket_id);
    }

    private Set<Ticket> filterByPriority(Set<Ticket> tickets, String priority) {
        if (priority == null) {
            return tickets;
        }

        return tickets
                .stream()
                .filter(ticket -> ticket.getPriority().name().equals(priority))
                .collect(Collectors.toSet());
    }
}
