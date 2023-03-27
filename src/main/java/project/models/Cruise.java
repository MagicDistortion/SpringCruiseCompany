package project.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "cruise")
public class Cruise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Positive(message = "Value must be positive")
    private int shipId;
    @Positive(message = "Value must be positive")
    private int routeId;
    @Positive(message = "Value must be positive")
    private int duration;
    @Positive(message = "Value must be positive")
    private int numberOfPorts;
    @Size(min = 2, max = 32, message = "ShipName must be between 2 and 32 characters")
    private String shipName;
    @Size(min = 2, max = 32, message = "CruiseName must be between 2 and 32 characters")
    private String cruiseName;
    private double price;
    @ElementCollection(targetClass = CruiseStatus.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "cruise_status", joinColumns = @JoinColumn(name = "cruise_id"))
    @Enumerated(EnumType.STRING)
    private Set<CruiseStatus> status;
    @FutureOrPresent(message = "Invalid date, It must be as future or present date")
    private LocalDateTime startTime;
    @FutureOrPresent(message = "Invalid date, It must be as future or present date")
    private LocalDateTime endTime;
    @NotEmpty(message = "Description must not be empty")
    private String description;

    public Cruise(int shipId, int routeId, String shipName, String cruiseName, int numberOfPorts
            , double price, LocalDateTime startTime, LocalDateTime endTime) {
        this.shipId = shipId;
        this.shipName = shipName;
        this.routeId = routeId;
        this.cruiseName = cruiseName;
        this.numberOfPorts = numberOfPorts;
        this.price = price;
        this.status = Collections.singleton(CruiseStatus.NOT_STARTED);
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = endTime.getDayOfYear() - startTime.getDayOfYear();
    }

    public String getStartTimeString() {
        return startTime.toLocalDate() + " " + startTime.toLocalTime();
    }

    public String getEndTimeString() {
        return endTime.toLocalDate() + " " + endTime.toLocalTime();
    }
}
