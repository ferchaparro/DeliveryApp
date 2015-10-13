package itson.mx.deliveryapp.recycler;

/**
 * Created by chapa on 09/10/2015.
 */
public class PlatilloDTO {
    private Long id;
    private String nombre;
    private String foto;

    public PlatilloDTO(){}
    public PlatilloDTO(Long id, String nombre, String foto){
        this.id=id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
