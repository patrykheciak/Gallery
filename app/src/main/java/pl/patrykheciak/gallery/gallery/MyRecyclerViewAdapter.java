package pl.patrykheciak.gallery.gallery;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import pl.patrykheciak.gallery.R;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private int[] mData;
    private LayoutInflater mInflater;
    private OnItemClickListener callback;

    MyRecyclerViewAdapter(Context context, int[] data) {
        this.callback = (OnItemClickListener) context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.gallery_grid_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.imgView.setImageResource(mData[position]);
    }

    @Override
    public int getItemCount() {
        return mData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgView;

        ViewHolder(View itemView) {
            super(itemView);

            imgView = itemView.findViewById(R.id.grid_img);
            imgView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (callback != null){
                        callback.onItemClick(view, getAdapterPosition());
                    }
                }
            });
        }

    }

    public interface OnItemClickListener {
        void onItemClick(View view, int adapterPosition);
    }

}