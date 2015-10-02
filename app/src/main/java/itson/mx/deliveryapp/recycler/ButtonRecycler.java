package itson.mx.deliveryapp.recycler;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import itson.mx.deliveryapp.R;

/**
 * Created by chapa on 02/10/2015.
 */
public class ButtonRecycler extends RecyclerView.Adapter<ButtonRecycler.DataObjectHolder> {
    private ArrayList<Boton> dataset;


    public static class DataObjectHolder extends RecyclerView.ViewHolder  {

        Button button;
        CardView cv;

        public DataObjectHolder(View itemView) {
            super(itemView);
            button = (Button) itemView.findViewById(R.id.btn_menu);
            cv = (CardView) itemView.findViewById(R.id.textView);
        }

    }


    public ButtonRecycler(ArrayList<Boton> dataset) {
        this.dataset = dataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.button.setText(dataset.get(position).getTexto());
        holder.button.setOnClickListener(dataset.get(position).getOnClickListener());
        holder.button.setBackgroundColor(dataset.get(position).getColor());
    }

    public void addItem(Boton dataObj, int index) {
        dataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        dataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

}