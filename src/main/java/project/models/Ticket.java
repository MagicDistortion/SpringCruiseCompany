package project.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Positive(message = "Value must be positive")
    private int cruiseId;
    @Size(min = 2,max = 32,message = "CruiseName should be between 2 and 32 characters")
    private String cruiseName;

    private int duration;
    @Positive(message = "Value must be positive")
    private int userId;
    @Positive(message = "Value must be positive")
    private int numberOfPassengers;
    private double totalPrice;
    private double price;
    @ElementCollection(targetClass = TicketStatus.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "ticket_status", joinColumns = @JoinColumn(name = "ticket_id"))
    @Enumerated(EnumType.STRING)
    private Set<TicketStatus> status;

    public Ticket(Cruise cruise, int userId, int numberOfPassengers) {
        this.cruiseId = cruise.getId();
        this.cruiseName = cruise.getCruiseName();
        this.duration = cruise.getDuration();
        this.price = cruise.getPrice();
        this.totalPrice = cruise.getPrice() * numberOfPassengers;
        this.userId = userId;
        this.numberOfPassengers = numberOfPassengers;
        this.status = Collections.singleton(TicketStatus.NOT_PAID);
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
        this.totalPrice = price * numberOfPassengers;
    }
}
