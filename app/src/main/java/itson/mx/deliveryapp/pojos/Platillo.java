package itson.mx.deliveryapp.pojos;

/**
 * Created by chapa on 21/11/2015.
 */
public class Platillo  {
    private Long id;
    private String nombre;
    private String descripcion;
    private TipoPlatillo tipoPlatillo;
    private Dia dia;
    private String foto;

    public Platillo(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoPlatillo getTipoPlatillo() {
        return tipoPlatillo;
    }

    public void setTipoPlatillo(TipoPlatillo tipoPlatillo) {
        this.tipoPlatillo = tipoPlatillo;
    }

    public Dia getDia() {
        return dia;
    }

    public void setDia(Dia dia) {
        this.dia = dia;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }



}

