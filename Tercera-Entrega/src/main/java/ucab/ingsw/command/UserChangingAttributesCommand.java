package ucab.ingsw.command;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.*;


@ToString
@Data //COMANDO QUE SE UTILIZA A LA HORA DE MODIFICAR EL PERFIL DEL USUARIO
public class UserChangingAttributesCommand implements Serializable{


    @Size(max = ValidationRules.FIRST_LAST_NAME_MAX_SIZE, message = "El nombre no puede contener más de 40 caracteres.")
    @Pattern(regexp = ValidationRules.FIRST_LAST_NAME_REGEX, message = "El nombre posee caracteres invalidos.")
    private String firstName;

    @Size(max = ValidationRules.FIRST_LAST_NAME_MAX_SIZE, message = "El nombre no puede contener más de 40 caracteres.")
    @Pattern(regexp = ValidationRules.FIRST_LAST_NAME_REGEX, message = "El apellido posee caracteres invalidos.")
    private String lastName;

    @NotNull(message = "Por favor, introduzca una dirección de correo.")
    @NotEmpty(message = "Por favor, introduzca una dirección de correo.")
    @Size(min = ValidationRules.EMAIL_MIN_SIZE, message = "La dirección de correo debe poseer al menos de 8 caracteres.")
    @Email(message = "error.format.email")
    private String email;

    @NotNull(message = "Por favor, introduzca una contraseña.")
    @NotEmpty(message = "Por favor, introduzca una contraseña.")
    @Size(min = ValidationRules.PASSWORD_MIN_SIZE, message = "La contraseña debe poseer al menos 8 caracteres.")
    private String password;

    @NotNull(message = "Por favor, introduzca una fecha de nacimiento.")
    @NotEmpty(message = "Por favor, introduzca una fecha de nacimiento.")
    private String dateOfBirth;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}