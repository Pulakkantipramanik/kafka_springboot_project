package com.example.ticketconsumerservice.repository;

import com.example.ticketconsumerservice.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    // Spring Data JPA automatically provides methods like save(), findById(), findAll(), etc.
}
