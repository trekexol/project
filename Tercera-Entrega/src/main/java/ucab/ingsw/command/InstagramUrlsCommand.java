package ucab.ingsw.command;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ToString
@Data
public class InstagramUrlsCommand {

    @NotNull(message = "Por favor, introduzca el token de Instagram.")
    @NotEmpty(message = "Por favor, introduzca el token de Instagram.")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
