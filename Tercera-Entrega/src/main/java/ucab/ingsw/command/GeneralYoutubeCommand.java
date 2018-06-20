package ucab.ingsw.command;


import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ToString
@Data
public class GeneralYoutubeCommand {

    @NotNull(message = "Por favor, introduzca el término de búsqueda.")
    @NotEmpty(message = "Por favor, introduzca el término de búsqueda.")
    private String searchTerm;

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }
}
