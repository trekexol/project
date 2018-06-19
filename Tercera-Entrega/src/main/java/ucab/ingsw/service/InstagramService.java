package ucab.ingsw.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ucab.ingsw.command.InstagramUrlsCommand;
import org.springframework.web.client.RestTemplate;
import ucab.ingsw.repository.*;
import ucab.ingsw.response.NotifyResponse;
import ucab.ingsw.dataApis.*;
import ucab.ingsw.response.InstagramUrlsResponse;
import ucab.ingsw.service.UserService;
import ucab.ingsw.model.User;

import java.time.LocalDateTime;
import java.util.*;



@Slf4j
@Service("InstagramService")
public class InstagramService {

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
            List<InstaData> dataPackage;
            RestTemplate restTemplate = new RestTemplate();
            InstagramUrl instagramInfo = restTemplate.getForObject(addressApi, InstagramUrl.class);
            dataPackage = instagramInfo.getData();
            if(dataPackage.isEmpty()){
                log.info("Search has not been sucessfull");

                return ResponseEntity.badRequest().body(buildNotifyResponse("no_result."));
            }
            else {
                log.info("Search has been successfull");

                InstagramUrlsResponse instagramResponse = new InstagramUrlsResponse();
                dataPackage.forEach(i -> {
                    instagramUrls.add(i.getImages().getStandard_resolution().getUrl());
                });
                instagramResponse.setUrls(instagramUrls);
                return ResponseEntity.ok(instagramResponse);
            }
        }

    }





