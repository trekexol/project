package ucab.ingsw.service;


import lombok.extern.slf4j.Slf4j;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import ucab.ingsw.dataApis.spotifyData.SpotifyArtists;
import ucab.ingsw.response.MediaUrlsResponse;
import ucab.ingsw.response.SpotifyResponse;
import ucab.ingsw.response.SongResponse;
import ucab.ingsw.dataApis.spotifyData.SpotifyData;
import ucab.ingsw.dataApis.spotifyData.Token;
import ucab.ingsw.dataApis.spotifyData.SpotifyTracks;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service("SpotifyService")
public class SpotifyService {


    public static final String CREDENTIALS= "OTM4ZGUxNDc1OTFmNGFhMmFjNDc" +
            "1N2Q0NWNhNDBjNmU6Y2NmOTQ3Mjk3NTBiNGI5NGEzNWY3M2FhMTVmN2NiMGM=";
    public static String ACCESS_TOKEN="TEMPLATE" ;
    public static final String TOTAL_RESULTS_FULL="5";
    public static final String TOTAL_RESULTS_URL="10";
    public static final String TOTAL_RESULTS_ARTISTS="8";

    private MediaUrlsResponse buildResponseTracksUrls(SpotifyTracks spotifyTracks){
        SpotifyResponse spotifyResponse=new SpotifyResponse();
        List<String> trackResponses = new ArrayList<>();
        MediaUrlsResponse urls = new MediaUrlsResponse();
        spotifyTracks.getItems().forEach( i-> {
            SongResponse songResponse =new SongResponse();
                    songResponse.setUrl(i.getExternal_urls().getSpotify());
                    trackResponses.add(songResponse.getUrl());
                }
        );
        urls.setUrls(trackResponses);
        return urls;
    }

    private SpotifyResponse buildResponseTracks(SpotifyTracks spotifyTracks){
        SpotifyResponse spotifyResponse=new SpotifyResponse();
        List<SongResponse> trackResponses = new ArrayList<>();
        spotifyTracks.getItems().forEach( i-> {
                    SongResponse songResponse =new SongResponse();
                    songResponse.setName(i.getName());
                    songResponse.setAlbum(i.getAlbum().getName());
                    songResponse.setAlbumImageUrl(i.getAlbum().getImages().get(1).getUrl());
                    List<String> artists = new ArrayList<>();
                    i.getArtists().forEach( j->{
                                artists.add(j.getName());
                            }
                    );
                    songResponse.setArtists(artists);
                    songResponse.setUrl(i.getExternal_urls().getSpotify());
                    trackResponses.add(songResponse);
                }
        );
        spotifyResponse.setTracks(trackResponses);

        return spotifyResponse;
    }

    private MediaUrlsResponse buildResponseArtists(SpotifyArtists spotifyArtists){
        List<String> trackResponses = new ArrayList<>();
        MediaUrlsResponse urls = new MediaUrlsResponse();
        spotifyArtists.getArtists().getItems().forEach( i-> {
                    SongResponse songResponse =new SongResponse();
                    songResponse.setUrl(i.getExternal_urls().getSpotify());
                    trackResponses.add(songResponse.getUrl());
                }
        );
        urls.setUrls(trackResponses);
        return urls;
    }


    public ResponseEntity<Object> searchArtists(String searchTerm) {
        String searchUrl="https://api.spotify.com/v1/search?q="+searchTerm+"&type=artist"+
                "&limit="+TOTAL_RESULTS_ARTISTS;
        ACCESS_TOKEN = generarToken();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers= new HttpHeaders();
        headers.add("Authorization","Bearer "+ACCESS_TOKEN);
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<SpotifyArtists> response=restTemplate.exchange(searchUrl,HttpMethod.GET,request,SpotifyArtists.class);
        SpotifyArtists spotifyData =response.getBody();
        if (spotifyData.getArtists().getItems().isEmpty()){
            log.info("No result for search term ={}", searchTerm);
            return ResponseEntity.badRequest().body("there is no result");
        }
        log.info("Returning results for search term ={}", searchTerm);
        return ResponseEntity.ok(buildResponseArtists(spotifyData));

    }


    public ResponseEntity<Object> searchUrls(String searchTerm) {
        String searchUrl="https://api.spotify.com/v1/search?type=track&q="+searchTerm+"&limit="+TOTAL_RESULTS_URL;
            ACCESS_TOKEN = generarToken();
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers= new HttpHeaders();
            headers.add("Authorization","Bearer "+ACCESS_TOKEN);
            HttpEntity<String> request = new HttpEntity<String>(headers);
            ResponseEntity<SpotifyData> response=restTemplate.exchange(searchUrl,HttpMethod.GET,request,SpotifyData.class);
            SpotifyData spotifyData =response.getBody();
            if (spotifyData.getTracks().getItems().isEmpty()){
                log.info("No result for search term ={}", searchTerm);
                return ResponseEntity.badRequest().body("there is no result");
            }
            log.info("Returning results for search term ={}", searchTerm);
            return ResponseEntity.ok(buildResponseTracksUrls(spotifyData.getTracks()));

    }


    public ResponseEntity<Object> search(String searchTerm) {
        String apiAddress="https://api.spotify.com/v1/search?type=track&q="+searchTerm+"&limit="+TOTAL_RESULTS_FULL;
                ACCESS_TOKEN = generarToken();
                RestTemplate restTemplate = new RestTemplate();
                HttpHeaders headers= new HttpHeaders();
                headers.add("Authorization","Bearer "+ACCESS_TOKEN);
                HttpEntity<String> request = new HttpEntity<String>(headers);
                ResponseEntity<SpotifyData> response=restTemplate.exchange(apiAddress,HttpMethod.GET,request,SpotifyData.class);
                SpotifyData spotifyData =response.getBody();
                if (spotifyData.getTracks().getItems().isEmpty()){
                    log.info("No result for search term ={}", searchTerm);
                    return ResponseEntity.badRequest().body("there is no result");
                }
                log.info("Returning results for search term ={}", searchTerm);
                return ResponseEntity.ok(buildResponseTracks(spotifyData.getTracks()));
    }

    public String generarToken() {
        String tokenUrl="https://accounts.spotify.com/api/token";
        RestTemplate tokenTemplate =new RestTemplate();
        HttpHeaders headers= new HttpHeaders();
        headers.add("Content-Type","application/x-www-form-urlencoded");
        headers.add("Authorization","Basic "+CREDENTIALS);
        MultiValueMap<String, String> params = new LinkedMultiValueMap();
        params.add("grant_type","client_credentials");
        HttpEntity<MultiValueMap<String, String>> tokenRequest = new HttpEntity(params,headers);
        ResponseEntity<Token> tokenResponse=tokenTemplate.exchange(tokenUrl,HttpMethod.POST,tokenRequest,Token.class);
        Token token =tokenResponse.getBody();
        ACCESS_TOKEN= token.getAccess_token();
        return ACCESS_TOKEN;
    }
}
