package ucab.ingsw.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucab.ingsw.command.*;
import ucab.ingsw.service.AlbumService;
import ucab.ingsw.service.YoutubeService;

import javax.validation.Valid;

@Slf4j

@CrossOrigin
@RestController
@RequestMapping(value = "/search", produces = "application/json")

public class YoutubeController {

    @Autowired
    private YoutubeService youtubeService;



    @RequestMapping(value = "/youtube/general", consumes = "application/json", method = RequestMethod.POST)
    public ResponseEntity getUrls(@Valid @RequestBody GeneralYoutubeCommand command) {
        return youtubeService.searchGeneral(command.getSearchTerm());
    }

    @RequestMapping(value = "/youtube/{id}", method = RequestMethod.GET)
    public ResponseEntity search(@PathVariable("id") String id) {
        return youtubeService.searchChannelUrls(id);
    }
}
