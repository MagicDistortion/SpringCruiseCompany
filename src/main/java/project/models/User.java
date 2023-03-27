package project.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import project.dto.UserRegistrationDTO;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "id"), @UniqueConstraint(columnNames = "username")})
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private String password;
    private String surname;
    private String name;
    private String tel;
    private LocalDate dateOfBirth;
    private double money;
    private boolean active;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> role;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRole();
    }


    public User(String username, String password, String surname, String name, String tel, LocalDate dateOfBirth) {
        this.username = username;
        this.password = password;
        this.surname = surname;
        this.name = name;
        this.tel = tel;
        this.dateOfBirth = dateOfBirth;
    }

    public User(UserRegistrationDTO userRegistrationDTO) {
        this.username = userRegistrationDTO.getUsername();
        this.password = userRegistrationDTO.getPassword();
        this.surname = userRegistrationDTO.getSurname();
        this.name = userRegistrationDTO.getName();
        this.tel = userRegistrationDTO.getTel();
        this.dateOfBirth = userRegistrationDTO.getDateOfBirth();
    }
}
