package ucab.ingsw.command;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@ToString
@Data
public class FriendListCommand {

    @NotNull(message = "Por favor, introduzca el id de su usuario.")
    @NotEmpty(message = "Por favor, introduzca el id de su usuario.")
    private String id;

}
