package itson.mx.deliveryapp.recycler;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;

/**
 * Created by chapa on 02/10/2015.
 */
public class Menu {
    private String texto;
    private int color;
    private View.OnClickListener onClickListener;
    private Drawable image;

    public Menu(){}
    public Menu(String texto, int color, View.OnClickListener onClickListener, Drawable image){
        this.texto=texto;
        this.color=color;
        this.onClickListener=onClickListener;
        this.image=image;
    }

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

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }
}
