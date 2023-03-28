package project.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import project.models.Routes;

public interface RouteRepo extends JpaRepository<Routes, Integer> {
}
