package project.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import project.models.Route;
import project.models.User;

public interface RouteRepo extends JpaRepository<Route, Integer> {
}
