package ucab.ingsw.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ucab.ingsw.dataApis.instagramData.InstagramDataUrls;
import ucab.ingsw.dataApis.instagramData.InstagramUrl;
import ucab.ingsw.repository.*;
import ucab.ingsw.response.NotifyResponse;
import ucab.ingsw.response.MediaUrlsResponse;
import ucab.ingsw.model.User;

import java.time.LocalDateTime;
import java.util.*;



@Slf4j
@Service("InstagramService")
public class InstagramService  {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

        private NotifyResponse buildNotifyResponse(String message){
            NotifyResponse response = new NotifyResponse();
            response.setMessage(message);
            response.setTimestamp(LocalDateTime.now());
            return response;
        }

        public ResponseEntity<Object> searchTag(String id, String instagramTag){
            User u = userService.searchUserById(id);
            String addressApi = "https://api.instagram.com/v1/tags/"+instagramTag+"/media/recent?access_token="+u.getInstagramToken();
            List<String> instagramUrls = new ArrayList<>();
            List<InstagramDataUrls> dataPackage;
            RestTemplate restTemplate = new RestTemplate();
            InstagramUrl instagramInfo = restTemplate.getForObject(addressApi, InstagramUrl.class);
            dataPackage = instagramInfo.getData();
            if(dataPackage.isEmpty()){
                log.info("Search has not been sucessfull");

                return ResponseEntity.badRequest().body(buildNotifyResponse("no_result."));
            }
            else {
                log.info("Search has been successfull");

                MediaUrlsResponse instagramResponse = new MediaUrlsResponse();
                dataPackage.forEach(i -> {
                    instagramUrls.add(i.getImages().getStandard_resolution().getUrl());
                });
                instagramResponse.setUrls(instagramUrls);
                return ResponseEntity.ok(instagramResponse);
            }
        }

    public boolean searchTag2(String id, String instagramTag){
        User u = userService.searchUserById(id);
        if (u==null) return false;
        String addressApi = "https://api.instagram.com/v1/tags/"+instagramTag+"/media/recent?access_token="+u.getInstagramToken();
        List<String> instagramUrls = new ArrayList<>();
        List<InstagramDataUrls> dataPackage;
        RestTemplate restTemplate = new RestTemplate();
        InstagramUrl instagramInfo = restTemplate.getForObject(addressApi, InstagramUrl.class);
        dataPackage = instagramInfo.getData();
        if(dataPackage.isEmpty()){
            log.info("Search has not been sucessfull");

            return false;
        }
        else {
            log.info("Search has been successfull");

            MediaUrlsResponse instagramResponse = new MediaUrlsResponse();
            dataPackage.forEach(i -> {
                instagramUrls.add(i.getImages().getStandard_resolution().getUrl());
            });
            instagramResponse.setUrls(instagramUrls);
            return true;
        }
    }

    }





