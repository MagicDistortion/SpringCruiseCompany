package project.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.print.Pageable;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@Table(name = "ship ",uniqueConstraints = {@UniqueConstraint(columnNames = "id"), @UniqueConstraint(columnNames = "name")})
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Size(min = 2,max = 32,message = "ShipName should be between 2 and 32 characters")
    private String name;
    @Positive(message = "Value must be positive")
    private int capacity;
    private String image;

    public Ship(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }
}
