package project.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import project.models.Ticket;

public interface TicketRepo extends JpaRepository<Ticket, Integer> {
}
