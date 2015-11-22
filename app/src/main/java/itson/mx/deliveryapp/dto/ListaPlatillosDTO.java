package itson.mx.deliveryapp.dto;

import java.util.List;

import itson.mx.deliveryapp.pojos.Platillo;

/**
 * Created by chapa on 21/11/2015.
 */
public class ListaPlatillosDTO {
    private List<Platillo> comunes;
    private List<Platillo> especiales;
    private List<Platillo> guarniciones;

    public ListaPlatillosDTO(){

    }
    public ListaPlatillosDTO(List<Platillo> comunes, List<Platillo> especiales, List<Platillo> guarniciones){
        this.comunes=comunes;
        this.especiales=especiales;
        this.guarniciones=guarniciones;
    }

    public List<Platillo> getComunes() {
        return comunes;
    }

    public void setComunes(List<Platillo> comunes) {
        this.comunes = comunes;
    }

    public List<Platillo> getEspeciales() {
        return especiales;
    }

    public void setEspeciales(List<Platillo> especiales) {
        this.especiales = especiales;
    }

    public List<Platillo> getGuarniciones() {
        return guarniciones;
    }

    public void setGuarniciones(List<Platillo> guarniciones) {
        this.guarniciones = guarniciones;
    }
}
