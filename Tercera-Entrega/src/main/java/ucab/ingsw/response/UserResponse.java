package ucab.ingsw.response;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class UserResponse {
    private Long id;
    private String firstName;    //OBJECTO QUE SE ENVÍA COMO RESPUESTA PARA LOS PROCESOS DEL USUARIO QUE
    private String lastName;     //NO TIENEN QUE VER CON EL PERFIL DEL USUARIO
    private String email;
    private String dateOfBirth;
    private String instagramToken;

    public String getInstagramToken() {
        return instagramToken;
    }

    public void setInstagramToken(String instagramToken) {
        this.instagramToken = instagramToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
