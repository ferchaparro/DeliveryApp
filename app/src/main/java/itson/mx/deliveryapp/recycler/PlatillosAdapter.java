package itson.mx.deliveryapp.recycler;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import itson.mx.deliveryapp.DetalleActivity;
import itson.mx.deliveryapp.R;

/**
 * Created by chapa on 02/10/2015.
 */
public class PlatillosAdapter extends RecyclerView.Adapter<PlatillosAdapter.ViewHolder> {
    private PlatilloDTO[] mDataset;
    private Context context;
    public static Map<Long, ImageView> cache;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView mTextView;
        CardView cv;
        ImageView image;
        ProgressBar progress;
        Button btnDetalle;
        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.txt_platillo);
            cv = (CardView) v.findViewById(R.id.cardview_platillo);
            image = (ImageView) v.findViewById(R.id.img_platillo);
            progress = (ProgressBar) v.findViewById(R.id.progressBar);
            btnDetalle = (Button) v.findViewById(R.id.btn_detalles);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public PlatillosAdapter(PlatilloDTO[] myDataset, Context context) {
        mDataset = myDataset; this.context=context;
        if(cache == null){
            cache = new HashMap<>();
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PlatillosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.platillo_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        //TODO
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset[position].getNombre());
        holder.progress.setIndeterminate(true);
        holder.btnDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cache.containsKey(mDataset[position].getId())) {
                    Intent intent = new Intent(context, DetalleActivity.class);
                    intent.putExtra("id", mDataset[position].getId());
                    intent.putExtra("nombre", mDataset[position].getNombre());
                    intent.putExtra("descripcion", "Luego se agregara la descripcion.");
                    context.startActivity(intent);
                }else{
                    Toast.makeText(context, "Espere que cargue antes de ver detalle.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        if(!cache.containsKey(mDataset[position].getId())) {
            holder.progress.setVisibility(View.VISIBLE);
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
                        Toast.makeText(context, "No se cargo la foto", Toast.LENGTH_SHORT).show();
                    }
                    holder.image.setImageBitmap(bitmap);
                    cache.put(mDataset[position].getId(), holder.image);
                    holder.progress.setVisibility(View.INVISIBLE);
                }
            };
            try {
                task.execute(mDataset[position].getFoto());
            } catch (Exception e) {
                e.printStackTrace();

            }
        }else{
            holder.image.setImageBitmap(((BitmapDrawable)cache.get(mDataset[position].getId()).getDrawable()).getBitmap());
            holder.progress.setVisibility(View.INVISIBLE);
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}