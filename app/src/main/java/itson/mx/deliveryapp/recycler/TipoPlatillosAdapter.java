package itson.mx.deliveryapp.recycler;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import itson.mx.deliveryapp.R;
import itson.mx.deliveryapp.dto.ListaPlatillosDTO;
import itson.mx.deliveryapp.pojos.Platillo;

/**
 * Created by chapa on 02/10/2015.
 */
public class TipoPlatillosAdapter extends RecyclerView.Adapter<TipoPlatillosAdapter.ViewHolder> {
    private String[] mDataset;
    private int color;
    private static Context context;
    private ListaPlatillosDTO listas;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView mTextView;
        CardView cv;
        RecyclerView mRecyclerView;
        RecyclerView.Adapter mAdapter;
        RecyclerView.LayoutManager mLayoutManager;
        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.textView);
            mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_platillo);
           cv = (CardView) v.findViewById(R.id.cardview_titulo);
            mLayoutManager = new LinearLayoutManager(v.getContext(), LinearLayoutManager.HORIZONTAL, false);

            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(mLayoutManager);


        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public TipoPlatillosAdapter(Context context, String[] myDataset, int color, ListaPlatillosDTO listas) {
        mDataset = myDataset;
        this.context=context;
        this.color = color;
        this.listas = listas;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public TipoPlatillosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_tipo_platillo, parent, false);
        // set the view's size, margins, paddings and layout parameters
        //TODO
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset[position]);
        holder.cv.setCardBackgroundColor(color);

        List<Platillo> dataset;
        /*
        switch (position){
            case 0: PlatilloDTO[] data = {
                        new PlatilloDTO(1L, "Lasagna", "http://www.eismann.es/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/1/4/14475.jpg"),
                        new PlatilloDTO(2L, "Chimichangas", "http://www.miscoloresysabores.com/wp-content/uploads/2011/09/IMG_1888.jpg"),
                        new PlatilloDTO(3L, "Sandwichon", "http://www.fooderific.com/thumbnails/300x300/463e476b43bf.jpg")
                    };dataset=data;break;
            case 1: PlatilloDTO[] data1 = {
                        new PlatilloDTO(4L, "Ceviche", "http://www.qassimy.com/up/users/qassimy/how_to_make_a_recipe_for_ceviche2.jpg"),
                        new PlatilloDTO(5L, "Rollo florentino", "http://www.reynolds.com.mx/wp-content/uploads/2012/08/Pastel-florentino-carne-des.jpg")
                    };dataset=data1;break;
            case 2: PlatilloDTO[] data2 = {
                        new PlatilloDTO(6L, "Frijol", "http://www.laben.mx/wp-content/uploads/2013/04/black-bean-dip-for-web1.jpg"),
                        new PlatilloDTO(7L, "Arroz", "http://i.ytimg.com/vi/SbxsGukLdyA/hqdefault.jpg"),
                        new PlatilloDTO(8L, "Ensalada", "http://www.marieldeviaje.com/wp-content/uploads/2015/08/ensalada.jpg"),
                        new PlatilloDTO(9L, "Pure de papa", "http://cocinamania.net/wp-content/uploads/2014/07/pure-de-papa.jpg")
                    };dataset=data2;break;
            default:dataset = new PlatilloDTO[0];
        }*/
        switch (position){
            case 0: dataset=listas.getComunes();break;
            case 1: dataset=listas.getEspeciales();break;
            case 2: dataset=listas.getEspeciales();break;
            default:dataset = new ArrayList<>();
        }

        holder.mAdapter = new PlatillosAdapter(dataset, context);
        holder.mRecyclerView.setAdapter(holder.mAdapter);





    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}