package ucab.ingsw.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString

public class Album implements Serializable {
    @Id
    private long id;

    private long identificador;
    private String nombreAlbum;
    private String descripcion;
    private List<Long> urls = new ArrayList<>();

    public List<Long> getUrls() {
        return urls;
    }

    public void setUrls(List<Long> urls) {
        this.urls = urls;
    }

    public String getNombreAlbum() {
        return nombreAlbum;
    }

    public void setNombreAlbum(String nombreAlbum) {
        this.nombreAlbum = nombreAlbum;
    }



    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }



    public void setId(long id) {
            this.id = id;
        }



        public long getId() {
            return id;
        }


    public long getIdentificador() {
        return identificador;
    }

    public void setIdentificador(long identificador) {
        this.identificador = identificador;
    }
}
