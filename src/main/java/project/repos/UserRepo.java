package project.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import project.models.User;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
