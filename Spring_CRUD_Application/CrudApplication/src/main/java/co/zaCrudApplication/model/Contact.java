package co.zaCrudApplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@RequiredArgsConstructor
@Entity
@NoArgsConstructor
public class Contact
{
    @Id
    @GeneratedValue
    private Integer user_id;

    @NonNull
    @NotBlank(message = "Enter your name.")
    @Size(min = 3, message = "Minimum characters is 3 chars.")
    private String name;

    @NonNull
    @NotBlank(message = "Enter your surname")
    private String surname;

    @NonNull
    @NotBlank(message = "Email cannot be blank, please enter email.")
    @Email(message = "Please enter appropriate email address.")
    private String email;

    @NonNull
    @NotBlank(message = "Password cannot be left blank.")
    @Size(min=8, message = "Password minimum size is 8 characters.")
    private String password;

}