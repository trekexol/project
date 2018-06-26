package ucab.ingsw.response;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class MediaUrlsResponse {
    private List<String> urls;
}
