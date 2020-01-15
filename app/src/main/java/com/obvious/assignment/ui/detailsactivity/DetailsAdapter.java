package com.obvious.assignment.ui.detailsactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.obvious.assignment.R;
import com.obvious.assignment.model.ImageModel;

import java.util.List;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder>{

    Context context;
    List<ImageModel> list;

    public DetailsAdapter(Context context, List<ImageModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.swipe_row, parent, false);
        return new DetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsViewHolder holder, int position) {

        try{

            Glide.with(context)
                    .load(list.get(position).getHdurl())
                    .into(holder.imageView);

            holder.title.setText(list.get(position).getTitle());
            holder.description.setText(list.get(position).getExplanation());
            holder.date.setText(list.get(position).getDate());

        }
        catch (Exception e){

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class DetailsViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView description, title, date;

        public DetailsViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            date = itemView.findViewById(R.id.date);
            description = itemView.findViewById(R.id.descp);
        }
    }
}
