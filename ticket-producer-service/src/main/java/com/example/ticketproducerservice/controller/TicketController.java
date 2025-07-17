package com.example.ticketproducerservice.controller;

import com.example.ticketproducerservice.dto.TicketRequest;
import com.example.ticketproducerservice.service.TicketProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tickets")
@Slf4j // For logging
public class TicketController {

    private final TicketProducerService ticketProducerService;

    public TicketController(TicketProducerService ticketProducerService) {
        this.ticketProducerService = ticketProducerService;
    }

    /**
     * Endpoint to create a new ticket.
     * It accepts a TicketRequest, sends it to Kafka, and returns a success response.
     * @param ticketRequest The ticket details from the client.
     * @return ResponseEntity indicating success or failure.
     */
    @PostMapping
    public ResponseEntity<String> createTicket(@RequestBody TicketRequest ticketRequest) {
        log.info("Received request to create ticket: {}", ticketRequest);
        try {
            ticketProducerService.sendTicketEvent(ticketRequest);
            return new ResponseEntity<>("Ticket creation request sent to Kafka successfully!", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            log.error("Error processing ticket creation request: {}", e.getMessage());
            return new ResponseEntity<>("Failed to process ticket creation request: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
