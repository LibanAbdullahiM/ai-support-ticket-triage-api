package com.liban.aisupporttickettriageapi.mapper;


import com.liban.aisupporttickettriageapi.dtos.request.TicketRequestDTO;
import com.liban.aisupporttickettriageapi.dtos.response.TicketResponseDTO;
import com.liban.aisupporttickettriageapi.model.Ticket;
import com.liban.aisupporttickettriageapi.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    Ticket toTicket(TicketRequestDTO ticketRequestDTO);

    TicketRequestDTO toTicketRequestDTO(Ticket ticket);

    @Mapping(source = "user", target = "createdByUserId")
    TicketResponseDTO toTicketResponseDTO(Ticket ticket);

    default UUID map(User user) {
        if (user == null) return null;
        return user.getId();
    }
}
