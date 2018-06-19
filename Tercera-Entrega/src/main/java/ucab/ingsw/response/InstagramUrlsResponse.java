package ucab.ingsw.response;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class InstagramUrlsResponse {
    private List<String> urls;
}
