package com.example.ticketproducerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Generates getters, setters, toString, equals, hashCode
@NoArgsConstructor // Generates a no-argument constructor
@AllArgsConstructor // Generates a constructor with all fields
public class TicketRequest {
    private String title;
    private String description;
    private String priority; // e.g., "HIGH", "MEDIUM", "LOW"
    private String status;   // e.g., "OPEN", "IN_PROGRESS", "CLOSED"
    private String createdBy;
}
