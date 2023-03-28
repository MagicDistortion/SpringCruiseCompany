package project.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShipDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Size(min = 2, max = 32, message = "ShipName should be between 2 and 32 characters")
    private String name;
    @Positive(message = "Value must be positive")
    private int capacity;

    public ShipDTO(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }
}
