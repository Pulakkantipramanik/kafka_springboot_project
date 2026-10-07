
# Kafka Spring Boot Microservices
This project is a simple Event-Driven Microservices Architecture using:
- Spring Boot 3.x
- Apache Kafka
- Docker & Docker Compose
- MySQL

Kafka Spring Boot Microservices

kafka-spring-boot-project/
├── docker-compose.yml
├── ticket-producer-service/
│   ├── pom.xml
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com/
│   │       │       └── example/
│   │       │           └── ticketproducerservice/
│   │       │               ├── TicketProducerServiceApplication.java  (Main Spring Boot app)
│   │       │               ├── controller/
│   │       │               │   └── TicketController.java
│   │       │               ├── dto/
│   │       │               │   └── TicketRequest.java
│   │       │               └── service/
│   │       │                   └── TicketProducerService.java
│   │       └── resources/
│   │           └── application.properties
│   └── 
└── ticket-consumer-service/
    ├── pom.xml
    ├── src/
    │   └── main/
    │       ├── java/
    │       │   └── com/
    │       │       └── example/
    │       │           └── ticketconsumerservice/
    │       │               ├── TicketConsumerServiceApplication.java  (Main Spring Boot app)
    │       │               ├── model/
    │       │               │   └── Ticket.java
    │       │               ├── repository/
    │       │               │   └── TicketRepository.java
    │       │               └── service/
    │       │                   └── TicketConsumerService.java
    │       └── resources/
    │           └── application.properties
    


