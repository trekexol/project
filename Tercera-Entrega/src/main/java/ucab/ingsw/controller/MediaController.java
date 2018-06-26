package ucab.ingsw.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucab.ingsw.command.UrlSignUpCommand;
import ucab.ingsw.service.MediaService;

import javax.validation.Valid;

@Slf4j

@CrossOrigin
@RestController
@RequestMapping(value = "/media", produces = "application/json")

public class MediaController {

        @Autowired
        private MediaService mediaService;

        @RequestMapping(value = "/register/{id}", consumes = "application/json", method = RequestMethod.POST)
        public ResponseEntity register(@Valid @RequestBody UrlSignUpCommand command, @PathVariable("id") String id) {
            return mediaService.register(command,id);
        }


    }


