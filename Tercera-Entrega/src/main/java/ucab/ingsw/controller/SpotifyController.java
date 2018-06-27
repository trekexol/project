package ucab.ingsw.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucab.ingsw.command.*;

import lombok.Data;
import ucab.ingsw.service.AlbumService;
import ucab.ingsw.service.SpotifyService;
import ucab.ingsw.service.YoutubeService;

import javax.validation.Valid;

@Slf4j
@Data
@CrossOrigin
@RestController
@RequestMapping(value = "/search", produces = "application/json")

public class SpotifyController {

    @Autowired
    private SpotifyService spotifyService;



    @RequestMapping(value = "/spotify/tracks", method = RequestMethod.GET)
    public ResponseEntity searchTracks(@RequestParam("query") String queryTrack) {

        queryTrack = queryTrack.replace(" ", "+");
            return spotifyService.search(queryTrack);
    }


    @RequestMapping(value = "/spotify/urls", method = RequestMethod.GET)
    public ResponseEntity searchUrls(@RequestParam("query") String queryTrack) {

        queryTrack = queryTrack.replace(" ", "+");
        return spotifyService.searchUrls(queryTrack);
    }

    @RequestMapping(value = "/spotify/artists", method = RequestMethod.GET)
    public ResponseEntity searchArtists(@RequestParam("query") String queryTrack) {

        queryTrack = queryTrack.replace(" ", "+");
        return spotifyService.searchArtists(queryTrack);
    }


}
