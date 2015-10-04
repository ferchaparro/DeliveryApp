package itson.mx.deliveryapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

import itson.mx.deliveryapp.overrides.GridLayoutManager;
import itson.mx.deliveryapp.recycler.Menu;
import itson.mx.deliveryapp.recycler.ButtonRecycler;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layourManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler = (RecyclerView) findViewById(R.id.my_recycler_view);
        recycler.setHasFixedSize(true);
        layourManager = new GridLayoutManager(this, 2);
        recycler.setLayoutManager(layourManager);
        adapter = new ButtonRecycler(getDataSet());
        recycler.setAdapter(adapter);

    }

    private ArrayList<Menu> getDataSet() {
        ArrayList results = new ArrayList<Menu>();
        results.add(new Menu("Lunes", Color.rgb(0, 150, 136), getOnClickMenu(MenuActivity.class, null), null));
        results.add(new Menu("Martes", Color.rgb(255, 87, 34), getOnClickMenu(MenuActivity.class, null), null));
        results.add(new Menu("Miercoles", Color.rgb(3, 169, 244), getOnClickMenu(MenuActivity.class, null), null));
        results.add(new Menu("Jueves", Color.rgb(76, 175, 80), getOnClickMenu(MenuActivity.class, null), null));
        results.add(new Menu("Viernes", Color.rgb(244, 67, 54), getOnClickMenu(MenuActivity.class, null), null));
        results.add(new Menu("Sabado", Color.rgb(103, 58, 183), getOnClickMenu(MenuActivity.class, null), null));

        return results;
    }

    private View.OnClickListener getOnClickMenu(final Class clazz, final Map<String, String> extras){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clazz!=null) {
                    Intent intent = new Intent(MainActivity.this, clazz);
                    intent.putExtra("color", ((android.graphics.drawable.ColorDrawable) v.getBackground()).getColor());
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
