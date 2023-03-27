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
public class UserEditDTO {
    @Size(min = 2, max = 32, message = "Surname must be between 2 and 32 characters")
    private String surname;
    @Size(min = 2, max = 32, message = "Name must be between 2 and 32 characters")
    private String name;
    @Size(min = 2, max = 32, message = "Username must be between 2 and 32 characters")
    private String username;
    @Size(min = 9, max = 12, message = "Phone number must be valid")
    private String tel;
    @PastOrPresent(message = "Invalid date, It must be as Past or present date")
    private LocalDate dateOfBirth;
}
