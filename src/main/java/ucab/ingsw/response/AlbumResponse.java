package ucab.ingsw.response;

import lombok.Data;
import lombok.ToString;
import ucab.ingsw.model.Media;


import java.util.List;

@Data
@ToString
public class AlbumResponse {
    private long id;
    private String name;
    private String description;
    private List<Long> media;
}
