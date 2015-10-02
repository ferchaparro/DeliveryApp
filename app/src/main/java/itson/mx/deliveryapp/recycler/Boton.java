package itson.mx.deliveryapp.recycler;

import android.view.View;
import android.widget.Button;

/**
 * Created by chapa on 02/10/2015.
 */
public class Boton {
    private String texto;
    private int color;
    private View.OnClickListener onClickListener;

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
