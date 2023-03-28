package project.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.dto.ShipDTO;

import java.awt.print.Pageable;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@Table(name = "ship ", uniqueConstraints = {@UniqueConstraint(columnNames = "id"), @UniqueConstraint(columnNames = "name")})
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int capacity;
    private String image;

    public Ship(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.image = name + ".jpeg";
    }

    public Ship(ShipDTO shipDTO) {
        this.name = shipDTO.getName();
        this.capacity = shipDTO.getCapacity();
        this.image = shipDTO.getName() + ".jpeg";
    }
}
