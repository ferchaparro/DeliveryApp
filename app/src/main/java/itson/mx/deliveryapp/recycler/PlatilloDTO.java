package itson.mx.deliveryapp.recycler;

/**
 * Created by chapa on 09/10/2015.
 */
public class PlatilloDTO {
    private String nombre;
    private String foto;

    public PlatilloDTO(){}
    public PlatilloDTO(String nombre, String foto){
        this.nombre = nombre;
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
