package project.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@PasswordsSameConstraint
@NoArgsConstructor
@AllArgsConstructor
public class UserPasswordDTO {
    @Size(min = 4, max = 32, message = "Password must be between 4 and 32 characters")
    private String password;
    @Size(min = 4, max = 32, message = "Password must be between 4 and 32 characters")
    private String repassword;
}
