package ucab.ingsw.service;




import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ucab.ingsw.model.Album;
import ucab.ingsw.repository.UserRepository;
import ucab.ingsw.response.NotifyResponse;
import ucab.ingsw.model.Media;
import ucab.ingsw.command.UrlSignUpCommand;
import ucab.ingsw.repository.*;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;




@Slf4j

@Service("MediaService")


public class MediaService {


        @Autowired
        private AlbumRepository albumRepository;

        @Autowired
        private MediaRepository mediaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @Autowired
    private AlbumService albumService;


        public ResponseEntity<Object> register(UrlSignUpCommand command,String id) { //SE ENCARGA DE REGISTRAR TODOS LOS USUARIOS
            log.debug("About to be processed [{}]", command);

            if (!albumRepository.existsById(Long.parseLong(id))) {
                log.info("El album no existe. ");

                return ResponseEntity.badRequest().body(buildNotifyResponse("El MediaController no existe."));
            } else {
                Album album = albumService.searchMediaById(id);
                Media media = new Media();
                media.setId(System.currentTimeMillis());
                media.setIdentificador(Long.parseLong(id));
                media.setUrl(command.getUrl());
                album.getUrls().add(media.getId());
                mediaRepository.save(media);
                albumRepository.save(album);
                log.info("Registered URL with ID={}", media.getId());
                return ResponseEntity.ok().body(buildNotifyResponse("Media registrada."));
            }

        }



        private NotifyResponse buildNotifyResponse(String message) { //MUESTRA UN MENSAJE DE NOTIFICACIÓN
            NotifyResponse respuesta = new NotifyResponse();
            respuesta.setMessage(message);
            respuesta.setTimestamp(LocalDateTime.now());
            return respuesta;
        }


    }


