package project.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import project.models.Ship;

public interface ShipRepo extends JpaRepository<Ship, Integer> {
}
