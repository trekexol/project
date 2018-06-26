package ucab.ingsw.service;

import ucab.ingsw.command.MediaSignUpCommand;
import ucab.ingsw.model.Album;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ucab.ingsw.model.User;
import ucab.ingsw.repository.AlbumRepository;
import ucab.ingsw.response.NotifyResponse;
import ucab.ingsw.repository.UserRepository;

import java.time.LocalDateTime;

@Slf4j

@Service("AlbumService")


public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public ResponseEntity<Object> registerMedia(MediaSignUpCommand command, String id) { //SE ENCARGA DE REGISTRAR TODOS LOS Albunes


        if (!userRepository.existsById(Long.parseLong(id))) {
            log.info("ID NO RECONOCIDA");

            return ResponseEntity.badRequest().body(buildNotifyResponse("ID NO RECONOCIDA"));
        } else  {

            User u = userService.searchUserById(id);
            Album album = new Album();

            album.setId(System.currentTimeMillis());
            album.setIdentificador(Long.parseLong(id));
            album.setNombreAlbum(command.getNombreAlbum());
            album.setDescripcion(command.getDescripcion());
            u.getAlbums().add(album.getId());
            album.setUrls(null);
            albumRepository.save(album);
            userRepository.save(u);

            log.info("Registered MediaController with ID={}", album.getId());

            return ResponseEntity.ok().body(buildNotifyResponse("MediaController registrado."));

        }
    }

    private NotifyResponse buildNotifyResponse(String message) { //MUESTRA UN MENSAJE DE NOTIFICACIÓN
        NotifyResponse respuesta = new NotifyResponse();
        respuesta.setMessage(message);
        respuesta.setTimestamp(LocalDateTime.now());
        return respuesta;
    }

    public Album searchMediaById(String id) {
        try {
            if(albumRepository.findById(Long.parseLong(id)).isPresent()){
                return albumRepository.findById(Long.parseLong(id)).get();
            }
            else
                return null;
        } catch (NumberFormatException e) {
            return null;
        }
    }


}
