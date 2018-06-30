package ucab.ingsw.command;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.*;

@ToString
@Data

public class UrlSignUpCommand implements Serializable {



    @NotNull(message = "Por favor, introduzca el URL")
    @NotEmpty(message = "Por favor, introduzca el URL")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
