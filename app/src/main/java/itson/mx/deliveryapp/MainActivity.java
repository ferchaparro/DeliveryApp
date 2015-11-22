package itson.mx.deliveryapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import itson.mx.deliveryapp.overrides.GridLayoutManager;
import itson.mx.deliveryapp.recycler.Menu;
import itson.mx.deliveryapp.recycler.ButtonRecycler;
import itson.mx.deliveryapp.service.CheckPlatillosService;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layourManager;
    private ProgressBar progress;
    private ImageView promo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        recycler = (RecyclerView) findViewById(R.id.my_recycler_view);
        recycler.setHasFixedSize(true);
        layourManager = new GridLayoutManager(this, 2);
        recycler.setLayoutManager(layourManager);
        adapter = new ButtonRecycler(getDataSet());
        recycler.setAdapter(adapter);
        progress = (ProgressBar)findViewById(R.id.progress_main);
        promo = (ImageView) findViewById(R.id.img_promo);

        progress.setVisibility(View.VISIBLE);
        progress.setIndeterminate(true);

        startService(new Intent(MainActivity.this, CheckPlatillosService.class));

        AsyncTask<String, Void, Bitmap> task = new AsyncTask<String, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(String... params) {
                try {
                    URL url = new URL(params[0]);
                    Bitmap loadedImage = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    return loadedImage;
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                if (bitmap == null) {
                    Toast.makeText(MainActivity.this, "No se cargo la promocion", Toast.LENGTH_SHORT).show();
                }
                promo.setImageBitmap(bitmap);
                progress.setVisibility(View.INVISIBLE);
            }
        };
        try {
            task.execute("https://scontent-lax3-1.xx.fbcdn.net/hphotos-xal1/v/t1.0-9/12105773_1665297650373502_3045004077835436874_n.jpg?oh=13922bac899ae4bc2a20b663806ad5ec&oe=56D1D333");

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    private ArrayList<Menu> getDataSet() {
        ArrayList results = new ArrayList<Menu>();
        results.add(new Menu("Lunes", Color.rgb(0, 150, 136), getOnClickMenu(MenuActivity.class, null, Color.rgb(0, 105, 92)), null));
        results.add(new Menu("Martes", Color.rgb(255, 87, 34), getOnClickMenu(MenuActivity.class, null, Color.rgb(216, 67, 21)), null));
        results.add(new Menu("Miercoles", Color.rgb(3, 169, 244), getOnClickMenu(MenuActivity.class, null, Color.rgb(2, 119, 189)), null));
        results.add(new Menu("Jueves", Color.rgb(76, 175, 80), getOnClickMenu(MenuActivity.class, null, Color.rgb(46, 125, 50)), null));
        results.add(new Menu("Viernes", Color.rgb(244, 67, 54), getOnClickMenu(MenuActivity.class, null, Color.rgb(198, 40, 40)), null));
        results.add(new Menu("Sabado", Color.rgb(103, 58, 183), getOnClickMenu(MenuActivity.class, null, Color.rgb(69, 39, 160)), null));

        return results;
    }

    private View.OnClickListener getOnClickMenu(final Class clazz, final Map<String, String> extras, final int colorDark){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clazz!=null) {
                    Intent intent = new Intent(MainActivity.this, clazz);
                    intent.putExtra("color", ((android.graphics.drawable.ColorDrawable) v.getBackground()).getColor());
                    intent.putExtra("colorDark", colorDark);
                    intent.putExtra("titulo", ((Button)v).getText());
                    if (extras != null) {
                        for (String key : extras.keySet()) {
                            intent.putExtra(key, extras.get(key));
                        }
                    }
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "Click en el boton "+((Button)v).getText(), Toast.LENGTH_SHORT).show();
                }
            }
        };
    }
}
