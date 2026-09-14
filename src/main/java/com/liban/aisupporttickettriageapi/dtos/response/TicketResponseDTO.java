package com.liban.aisupporttickettriageapi.dtos.response;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@ToString
public class TicketResponseDTO {

    private String title;
    private String description;
    private String aiSuggestedReply;
    private String status;
    private LocalDate createdAt;

    private UUID createdByUserId;
}
