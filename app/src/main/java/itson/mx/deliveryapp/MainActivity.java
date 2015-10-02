package itson.mx.deliveryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

import itson.mx.deliveryapp.recycler.Boton;
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

    private ArrayList<Boton> getDataSet() {
        ArrayList results = new ArrayList<Boton>();
        for (int index = 0; index < 5; index++) {
            final int i = index;
            Boton obj = new Boton();
            obj.setTexto("Menu"+index);
            if(index == 0) {
                obj.setOnClickListener(getOnClickMenu(MenuActivity.class, null));
            }else{
                obj.setOnClickListener(getOnClickMenu(null, null));
            }
            results.add(index, obj);
        }
        return results;
    }

    private View.OnClickListener getOnClickMenu(final Class clazz, final Map<String, String> extras){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clazz!=null) {
                    Intent intent = new Intent(MainActivity.this, clazz);
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
