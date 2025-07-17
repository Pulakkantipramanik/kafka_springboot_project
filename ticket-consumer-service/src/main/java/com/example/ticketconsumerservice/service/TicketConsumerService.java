package com.example.ticketconsumerservice.service;

import com.example.ticketconsumerservice.model.Ticket;
import com.example.ticketconsumerservice.repository.TicketRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j // For logging
public class TicketConsumerService {

    private final TicketRepository ticketRepository;
    private final ObjectMapper objectMapper; // To convert JSON string back to object

    public TicketConsumerService(TicketRepository ticketRepository, ObjectMapper objectMapper) {
        this.ticketRepository = ticketRepository;
        this.objectMapper = objectMapper;
    }

    /**
     * Kafka listener method to consume messages from the 'ticket-events' topic.
     * The message is expected to be a JSON string representing a Ticket object.
     * @param message The JSON string message received from Kafka.
     */
    @KafkaListener(topics = "${app.kafka.topic.ticket-events}", groupId = "${spring.kafka.consumer.group-id}")
    public void listenTicketEvents(String message) {
        log.info("Received message from Kafka: {}", message);
        try {
            // Convert JSON string back to Ticket object
            Ticket ticket = objectMapper.readValue(message, Ticket.class);
            // Set ID to null to ensure JPA creates a new entry
            ticket.setId(null);
            // Save the ticket to MySQL database
            Ticket savedTicket = ticketRepository.save(ticket);
            log.info("Ticket saved to database with ID: {}", savedTicket.getId());
        } catch (Exception e) {
            log.error("Error processing Kafka message: {} - {}", message, e.getMessage());
            // Log the error but don't rethrow to avoid stopping the consumer
            // Consider dead-letter queue (DLQ) for failed messages in a production setup
        }
    }
}