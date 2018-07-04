package ucab.ingsw.command;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ToString
@Data
public class ListFriendCommand {

    @NotNull(message = "Por favor, introduzca la contraseña del usuario.")
    @NotEmpty(message = "Por favor, introduzca la contraseña del usuario.")
    private String contraseña;

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
