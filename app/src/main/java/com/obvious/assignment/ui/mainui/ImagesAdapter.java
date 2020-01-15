package com.obvious.assignment.ui.mainui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.obvious.assignment.R;
import com.obvious.assignment.model.ImageModel;

import java.util.List;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ImagesViewHolder> {

    List<ImageModel> list;
    Context context;
    OnImageClick onImageClickListener;

    public ImagesAdapter(List<ImageModel> list, Context context, OnImageClick onImageClickListener) {
        this.list = list;
        this.context = context;
        this.onImageClickListener = onImageClickListener;
    }


    @NonNull
    @Override
    public ImagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_row, parent, false);
        ImagesViewHolder imagesViewHolder = new ImagesViewHolder(view, onImageClickListener);
        return imagesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesViewHolder holder, int position) {

        Glide.with(context)
                .load(list.get(position).getUrl())
                .into(holder.image);

        holder.text.setText(list.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ImagesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView image;
        TextView text;
        CardView cardView;
        OnImageClick onImageClick;

        public ImagesViewHolder(@NonNull View itemView, OnImageClick onImageClick) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardview_id);
            image = itemView.findViewById(R.id.picImage);
            text = itemView.findViewById(R.id.picTitle);
            cardView.setOnClickListener(this);
            this.onImageClick = onImageClick;

        }

        @Override
        public void onClick(View v) {

            if(v.getId()==cardView.getId()){
            onImageClick.onClick(getAdapterPosition());
            }

        }
    }

    public interface OnImageClick{

        void onClick(int position);
    }
}
