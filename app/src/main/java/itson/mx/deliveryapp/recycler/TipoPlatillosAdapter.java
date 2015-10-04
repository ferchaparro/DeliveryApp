package itson.mx.deliveryapp.recycler;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import itson.mx.deliveryapp.R;

/**
 * Created by chapa on 02/10/2015.
 */
public class TipoPlatillosAdapter extends RecyclerView.Adapter<TipoPlatillosAdapter.ViewHolder> {
    private String[] mDataset;
    private int color;

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

            String[] dataset = { "uno", "dos", "tres", "cuatro", "cinco", "seis" };
            mAdapter = new PlatillosAdapter(dataset);
            mRecyclerView.setAdapter(mAdapter);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public TipoPlatillosAdapter(String[] myDataset, int color) {
        mDataset = myDataset;
        this.color = color;
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





    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}