package project.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import project.models.Cruise;

public interface CruiseRepo extends JpaRepository<Cruise, Integer> {
}
