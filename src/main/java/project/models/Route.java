package project.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@NoArgsConstructor
@Table(name = "route", uniqueConstraints = {@UniqueConstraint(columnNames = "id"), @UniqueConstraint(columnNames = "name")})
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Size(min = 2,max = 32,message = "RouteName must be between 2 and 32 characters")
    private String name;
    @NotEmpty(message = "Route should not be empty")
    private String route;
    @Transient
    private List<String> list;

    public Route(String name, String route) {
        this.name = name;
        this.route = route;
        this.list = Arrays.stream(route.split(",")).collect(Collectors.toList());
    }
}
