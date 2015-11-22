package itson.mx.deliveryapp;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.siscom.solicitudasyncandroid.async.AsyncCallback;
import com.siscom.solicitudasyncandroid.async.SolicitudAsync;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import itson.mx.deliveryapp.dto.ListaPlatillosDTO;
import itson.mx.deliveryapp.pojos.Dia;
import itson.mx.deliveryapp.recycler.TipoPlatillosAdapter;

/**
 * Created by chapa on 02/10/2015.
 */
public class MenuActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final int color = getIntent().getIntExtra("color", 0);
        int colorDark = getIntent().getIntExtra("colorDark", 0);
        String dia = getIntent().getStringExtra("titulo");
        getSupportActionBar().setTitle(dia);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(color));
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            //getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().setStatusBarColor(colorDark);
        }
        setContentView(R.layout.activiry_menu);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_menu);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        SolicitudAsync<ListaPlatillosDTO, ListaPlatillosDTO> solicitud = new SolicitudAsync<>(MenuActivity.this, "http://1-dot-deliveryapp-1086.appspot.com/main", new AsyncCallback<ListaPlatillosDTO>() {
            @Override
            public void onSuccess(ListaPlatillosDTO listas) {

                String[] dataset = { "Platillos regulares", "Especiales", "Guarniciones" };
                mAdapter = new TipoPlatillosAdapter(MenuActivity.this, dataset, color, listas);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFail(Exception e) {
                Toast.makeText(MenuActivity.this, "Ocurrio un error intenta mas tarde...", Toast.LENGTH_SHORT).show();
            }
        }, false, new Handler(), false, ListaPlatillosDTO.class, null, "espera...");
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("action", "getPlatillosDia"));
        params.add(new BasicNameValuePair("dia", getDiaString(dia)));
        solicitud.execute(params);


    }

    private String getDiaString(String dia){
        String s;
        switch(dia){
            case "Lunes":s = Dia.LUNES.name();break;
            case "Martes":s = Dia.MARTES.name();break;
            case "Miercoles":s = Dia.MIERCOLELS.name();break;
            case "Jueves":s = Dia.JUEVES.name();break;
            case "Viernes":s = Dia.VIERNES.name();break;
            case "Sabado":s = Dia.SABADO.name();break;
            case "Domingo":s = Dia.DOMINGO.name();break;
            default: s="";
        }
        return s;
    }

}
