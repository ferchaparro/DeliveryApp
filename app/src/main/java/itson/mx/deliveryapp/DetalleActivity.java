package itson.mx.deliveryapp;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import itson.mx.deliveryapp.recycler.PlatillosAdapter;

/**
 * Created by chapa on 12/10/2015.
 */
public class DetalleActivity extends AppCompatActivity {
    private ImageView imgDetalle;
    private TextView txtNombre, txtDescripcion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_platillo);
        txtDescripcion = (TextView) findViewById(R.id.txt_desc);
        txtNombre = (TextView) findViewById(R.id.txt_nombre);
        imgDetalle = (ImageView) findViewById(R.id.img_detalle);

        txtNombre.setText(getIntent().getStringExtra("nombre"));
        txtDescripcion.setText(getIntent().getStringExtra("descripcion"));
        imgDetalle.setImageBitmap(((BitmapDrawable) PlatillosAdapter.cache.get(getIntent().getLongExtra("id", 0)).getDrawable()).getBitmap());
    }
}
