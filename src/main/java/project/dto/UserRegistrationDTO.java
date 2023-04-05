package project.dto;

import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@PasswordsSameConstraint
public class UserRegistrationDTO {
    @Size(min = 2, max = 32, message = "Surname must be between 2 and 32 characters")
//    @Size(min = 2, max = 32, message = "${blabla}")
    private String surname;
    @Size(min = 2, max = 32, message = "Name must be between 2 and 32 characters")
    private String name;
    @Size(min = 2, max = 32, message = "Username must be between 2 and 32 characters")
    private String username;
    @Size(min = 4, max = 32, message = "Password must be between 4 and 32 characters")
    private String password;
    @Size(min = 4, max = 32, message = "Password must be between 4 and 32 characters")
    private String repassword;
    @Size(min = 9, max = 12, message = "Phone number must be valid")
    private String tel;
    @PastOrPresent(message = "Invalid date, It must be as Past or present date")
    private LocalDate dateOfBirth;
}
