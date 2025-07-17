package com.example.ticketconsumerservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
@Data // Generates getters, setters, toString, equals, hashCode
@NoArgsConstructor // Generates a no-argument constructor
@AllArgsConstructor // Generates a constructor with all fields
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT") // For potentially longer descriptions
    private String description;

    private String priority; // e.g., "HIGH", "MEDIUM", "LOW"

    private String status;   // e.g., "OPEN", "IN_PROGRESS", "CLOSED"

    @Column(name = "created_by")
    private String createdBy;

    @CreationTimestamp // Automatically sets creation timestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
