package com.example.ticketproducerservice.service;
import com.example.ticketproducerservice.dto.TicketRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j // For logging
public class TicketProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper; // To convert DTO to JSON string

    @Value("${app.kafka.topic.ticket-events}")
    private String topicName;

    public TicketProducerService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    /**
     * Sends a TicketRequest object as a JSON string to the Kafka topic.
     * @param ticketRequest The ticket request DTO to send.
     */
    public void sendTicketEvent(TicketRequest ticketRequest) {
        try {
            // Convert TicketRequest object to JSON string
            String ticketJson = objectMapper.writeValueAsString(ticketRequest);
            log.info("Sending message to Kafka topic '{}': {}", topicName, ticketJson);
            // Send the JSON string to the Kafka topic.
            // The key is null here, Kafka will distribute messages based on partitions.
            kafkaTemplate.send(topicName, ticketJson);
            log.info("Message sent successfully.");
        } catch (JsonProcessingException e) {
            log.error("Error converting TicketRequest to JSON: {}", e.getMessage());
            // You might want to throw a custom exception or handle it differently
            throw new RuntimeException("Failed to serialize ticket request", e);
        } catch (Exception e) {
            log.error("Error sending message to Kafka: {}", e.getMessage());
            throw new RuntimeException("Failed to send message to Kafka", e);
        }
    }
}