package ucab.ingsw.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ucab.ingsw.dataApis.youtubeData.*;
import ucab.ingsw.repository.UserRepository;
import ucab.ingsw.response.MediaUrlsResponse;
import ucab.ingsw.response.NotifyResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import ucab.ingsw.model.User;


@Slf4j
@Service("YoutubeService")
public class YoutubeService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    String searchTerm = new String();


        private NotifyResponse buildNotifyResponse(String message){
            NotifyResponse response = new NotifyResponse();
            response.setMessage(message);
            response.setTimestamp(LocalDateTime.now());
            return response;
        }

    public ResponseEntity<Object> searchGeneral(String termino){
        String addressApi= "https://www.googleapis.com/youtube/v3/search?part=snippet&q="+termino+"&key=AIzaSyBnZJwOtyGQZtE5epo1MR-fYht1p6XW1V8";
        List<String> youtubeUrls = new ArrayList<>();
        List<YoutubeDataUrls> dataPackage;
        RestTemplate restTemplate = new RestTemplate();
        YoutubeUrl youtubeInfo = restTemplate.getForObject(addressApi, YoutubeUrl.class);
        dataPackage = youtubeInfo.getItems();
        if(dataPackage.isEmpty()){
            log.info("Search has not been sucessfull");
            return ResponseEntity.badRequest().body(buildNotifyResponse("no_result."));
        }
        else {
            log.info("Search has been successfull");
            MediaUrlsResponse youtubeResponse = new MediaUrlsResponse();
            dataPackage.forEach(i -> {
                youtubeUrls.add("https://www.youtube.com/watch?v="+i.getId().getVideoId());
            });
            youtubeResponse.setUrls(youtubeUrls);
            return ResponseEntity.ok(youtubeResponse);
        }
    }

    public ResponseEntity<Object> searchChannelUrls(String id){
        User u = userService.searchUserById(id);
        String addressApi = "https://www.googleapis.com/youtube/v3/search?part=snippet&channelId="+u.getYoutubeChannelId()+"&maxResults=50&key=AIzaSyBnZJwOtyGQZtE5epo1MR-fYht1p6XW1V8";
        List<String> youtubeUrls = new ArrayList<>();
        List<YoutubeDataUrls> dataPackage;
        RestTemplate restTemplate = new RestTemplate();
        YoutubeUrl instagramInfo = restTemplate.getForObject(addressApi, YoutubeUrl.class);
        dataPackage = instagramInfo.getItems();
        if(dataPackage.isEmpty()){
            log.info("Search has not been sucessfull");

            return ResponseEntity.badRequest().body(buildNotifyResponse("no_result."));
        }
        else {
            log.info("Search has been successfull");

            MediaUrlsResponse instagramResponse = new MediaUrlsResponse();
            dataPackage.forEach(i -> {
                youtubeUrls.add("https://www.youtube.com/watch?v="+i.getId().getVideoId());
            });
            instagramResponse.setUrls(youtubeUrls);
            return ResponseEntity.ok(instagramResponse);
        }
    }


}





