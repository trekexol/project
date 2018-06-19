package ucab.ingsw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucab.ingsw.command.InstagramUrlsCommand;
import ucab.ingsw.service.InstagramService;

import javax.validation.Valid;


@CrossOrigin
@RestController
@RequestMapping(value = "/search", produces = "application/json")
public class InstagramController {

    @Autowired
    private InstagramService instagramService;


    @RequestMapping(value = "/instagram/{id}", method = RequestMethod.GET)
    public ResponseEntity search(@PathVariable("id") String id, @RequestParam("query") String instagramTag) {
            instagramTag = instagramTag.replace(" ", "");
            return instagramService.searchTag(id, instagramTag);
        }

    }


