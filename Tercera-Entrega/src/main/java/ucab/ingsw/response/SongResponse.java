package ucab.ingsw.response;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class SongResponse {
    private String name;
    private List<String> artists;
    private String album;
    private String albumImageUrl;
    private String url;
}
