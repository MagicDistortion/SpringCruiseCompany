package project.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Data
@Entity
@NoArgsConstructor
@Table(name = "route", uniqueConstraints = {@UniqueConstraint(columnNames = "id"), @UniqueConstraint(columnNames = "name")})
public class Routes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Size(min = 2, max = 32, message = "RouteName must be between 2 and 32 characters")
    private String name;
    @NotEmpty(message = "Route must not be empty")
    @Pattern(regexp ="([0-9A-Za-zА-Яа-яІіЇїєЄ ]+;)+",message = "The route must be written via ;")
    private String route;
    @Transient
    private int size;

    public Routes(String name, String route) {
        this.name = name;
        this.route = route;
        this.size = Arrays.stream(route.split(";")).toList().size();
    }
}
