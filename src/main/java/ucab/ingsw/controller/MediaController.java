package ucab.ingsw.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucab.ingsw.command.MediaSignUpCommand;
import ucab.ingsw.service.MediaService;
import ucab.ingsw.command.DeleteMediaCommand;

import javax.validation.Valid;

@Slf4j

@CrossOrigin
@RestController
@RequestMapping(value = "/media", produces = "application/json")

public class MediaController {

        @Autowired
        private MediaService mediaService;

        @RequestMapping(value = "/register/{id}", consumes = "application/json", method = RequestMethod.POST)
        public ResponseEntity register(@Valid @RequestBody MediaSignUpCommand command, @PathVariable("id") String id) {
            return mediaService.registerMedia(command,id);
        }

   @RequestMapping(value = "/delete/{id}/{id2}", consumes = "application/json", method = RequestMethod.POST)
    public ResponseEntity delete(@Valid @RequestBody DeleteMediaCommand command, @PathVariable("id") String id, @PathVariable("id2") String idUser) {
        return mediaService.deleteMedia(command,id, idUser);
    }

    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
    public ResponseEntity list(@PathVariable("id") String id) {
        return mediaService.MediaList(id);
    }

}


